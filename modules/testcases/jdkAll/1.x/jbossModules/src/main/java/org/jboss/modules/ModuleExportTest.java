/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.modules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.modules.filter.PathFilters;
import org.jboss.modules.test.ImportedClass;
import org.jboss.modules.util.TestModuleLoader;
import org.jboss.modules.util.TestResourceLoader;
import org.junit.Ignore;
import org.junit.Test;
import org.jboss.eap.additional.testsuite.annotations.EapAdditionalTestsuite;

@EapAdditionalTestsuite("modules/testcases/jdkAll/1.x/jbossModules/src/main/java")
/**
 * Test to verify the module export dependencies and imports are created correctly.  Each module should have an entry
 * directly to the module that has an exported path.
 *
 * @author John E. Bailey
 */
public class ModuleExportTest extends AbstractModuleTestCase {

    private static final ModuleIdentifier MODULE_A = ModuleIdentifier.fromString("a");
    private static final ModuleIdentifier MODULE_B = ModuleIdentifier.fromString("b");
    private static final ModuleIdentifier MODULE_C = ModuleIdentifier.fromString("c");
    private static final ModuleIdentifier MODULE_D = ModuleIdentifier.fromString("d");

    @Test
    public void testExportDependencies() throws Exception {
        final TestModuleLoader moduleLoader = new TestModuleLoader();

        ModuleSpec.Builder builder = ModuleSpec.build(MODULE_A);
        builder.addDependency(new ModuleDependencySpecBuilder()
            .setName(MODULE_B.toString())
            .setExport(true)
            .build());
        moduleLoader.addModuleSpec(builder.create());

        builder = ModuleSpec.build(MODULE_B);
        builder.addDependency(new ModuleDependencySpecBuilder()
            .setName(MODULE_C.toString())
            .setExport(true)
            .build());
        builder.addDependency(new ModuleDependencySpecBuilder()
            .setName(MODULE_D.toString())
            .build());
        moduleLoader.addModuleSpec(builder.create());

        builder = ModuleSpec.build(MODULE_C);
        moduleLoader.addModuleSpec(builder.create());

        builder = ModuleSpec.build(MODULE_D);
        moduleLoader.addModuleSpec(builder.create());

        Module module = moduleLoader.loadModule(MODULE_A);
        final Set<ModuleIdentifier> dependencyExports = new HashSet<ModuleIdentifier>();
        getExportedModuleDeps(module, dependencyExports);
        assertEquals(2, dependencyExports.size());
        assertTrue(dependencyExports.contains(MODULE_B));
        assertTrue(dependencyExports.contains(MODULE_C));

        dependencyExports.clear();
        module = moduleLoader.loadModule(MODULE_B);
        getExportedModuleDeps(module, dependencyExports);
        assertEquals(1, dependencyExports.size());
        assertTrue(dependencyExports.contains(MODULE_C));

        dependencyExports.clear();
        module = moduleLoader.loadModule(MODULE_C);
        getExportedModuleDeps(module, dependencyExports);
        assertEquals(0, dependencyExports.size());

        dependencyExports.clear();
        module = moduleLoader.loadModule(MODULE_D);
        getExportedModuleDeps(module, dependencyExports);
        assertEquals(0, dependencyExports.size());
    }

    private static void getExportedModuleDeps(final Module module, final Set<ModuleIdentifier> dependencyExports) throws ModuleLoadException {
        getExportedModuleDeps(module, new HashSet<Module>(Collections.singleton(module)), dependencyExports);
    }

    private static void getExportedModuleDeps(final Module module, final Set<Module> visited, final Set<ModuleIdentifier> dependencyExports) throws ModuleLoadException {
        for (Dependency dependency : module.getDependenciesInternal()) {
            if (dependency instanceof ModuleDependency && dependency.getExportFilter() != PathFilters.rejectAll()) {
                final ModuleDependency moduleDependency = (ModuleDependency) dependency;
                final Module md = moduleDependency.getModuleLoader().loadModule(moduleDependency.getName());
                if (md != null && moduleDependency.getExportFilter() != PathFilters.rejectAll()) {
                    if (visited.add(md)) {
                        dependencyExports.add(md.getIdentifier());
                        getExportedModuleDeps(md, visited, dependencyExports);
                    }
                }
            }
        }
    }

    @SuppressWarnings({ "unchecked" })
    @Test
    @Ignore("Disabled; relies on modules not inheriting full set of JDK paths which is incorrect behavior")
    public void testImportPaths() throws Exception {
        final TestModuleLoader moduleLoader = new TestModuleLoader();

        ModuleSpec.Builder builder = ModuleSpec.build(MODULE_A);
        builder.addDependency(new ModuleDependencySpecBuilder()
            .setName(MODULE_B.toString())
            .setExport(true)
            .build());
        moduleLoader.addModuleSpec(builder.create());

        builder = ModuleSpec.build(MODULE_B);
        builder.addDependency(new ModuleDependencySpecBuilder()
            .setName(MODULE_C.toString())
            .setExport(true)
            .build());
        builder.addDependency(new ModuleDependencySpecBuilder()
            .setName(MODULE_D.toString())
            .build());
        moduleLoader.addModuleSpec(builder.create());

        builder = ModuleSpec.build(MODULE_C);
        builder.addResourceRoot(ResourceLoaderSpec.createResourceLoaderSpec(TestResourceLoader.build()
            .addClass(ImportedClass.class)
            .create()
        ));
        builder.addDependency(DependencySpec.createLocalDependencySpec());
        moduleLoader.addModuleSpec(builder.create());

        builder = ModuleSpec.build(MODULE_D);
        moduleLoader.addModuleSpec(builder.create());

        Module module = moduleLoader.loadModule(MODULE_A);
        module.getClassLoader().loadClass(ImportedClass.class.getName());

        final Field pathsField = Module.class.getDeclaredField("linkage");
        pathsField.setAccessible(true);
        final Object paths = pathsField.get(module);
        final Field allPathsField = paths.getClass().getDeclaredField("allPaths");
        allPathsField.setAccessible(true);
        final Map<String, List<LocalLoader>> allPaths = (Map<String, List<LocalLoader>>) allPathsField.get(paths);

        Module moduleC = moduleLoader.loadModule(MODULE_C);

        assertEquals(4, allPaths.size());

        for(Map.Entry<String, List<LocalLoader>> entry : allPaths.entrySet()) {
            assertEquals(1, entry.getValue().size());
            assertEquals(moduleC.getClassLoaderPrivate().getLocalLoader(), entry.getValue().get(0));
        }
    }
}
