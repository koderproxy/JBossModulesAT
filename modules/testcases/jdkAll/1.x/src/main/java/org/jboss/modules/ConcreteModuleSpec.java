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

import java.security.PermissionCollection;
import java.util.Map;

/**
 * A {@code Module} specification for a concrete module implementation.
 *
 * @apiviz.exclude
 *
 * @author <a href="mailto:jbailey@redhat.com">John Bailey</a>
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public final class ConcreteModuleSpec extends ModuleSpec {

    private final String mainClass;
    private final AssertionSetting assertionSetting;
    private final ResourceLoaderSpec[] resourceLoaders;
    private final DependencySpec[] dependencies;
    private final LocalLoader fallbackLoader;
    private final ModuleClassLoaderFactory moduleClassLoaderFactory;
    private final ClassTransformer classFileTransformer;
    private final Map<String, String> properties;
    private final PermissionCollection permissionCollection;
    private final Version version;

    ConcreteModuleSpec(final String name, final String mainClass, final AssertionSetting assertionSetting, final ResourceLoaderSpec[] resourceLoaders, final DependencySpec[] dependencies, final LocalLoader fallbackLoader, final ModuleClassLoaderFactory moduleClassLoaderFactory, final ClassTransformer classFileTransformer, final Map<String, String> properties, final PermissionCollection permissionCollection, final Version version) {
        super(name);
        this.mainClass = mainClass;
        this.assertionSetting = assertionSetting;
        this.resourceLoaders = resourceLoaders;
        this.dependencies = dependencies;
        this.fallbackLoader = fallbackLoader;
        this.moduleClassLoaderFactory = moduleClassLoaderFactory;
        this.classFileTransformer = classFileTransformer;
        this.properties = properties;
        this.permissionCollection = permissionCollection;
        this.version = version;
    }

    public String getMainClass() {
        return mainClass;
    }

    AssertionSetting getAssertionSetting() {
        return assertionSetting;
    }

    ResourceLoaderSpec[] getResourceLoaders() {
        return resourceLoaders;
    }

    DependencySpec[] getDependenciesInternal() {
        return dependencies;
    }

    public DependencySpec[] getDependencies() {
        return dependencies.length == 0 ? dependencies : dependencies.clone();
    }

    LocalLoader getFallbackLoader() {
        return fallbackLoader;
    }

    ModuleClassLoaderFactory getModuleClassLoaderFactory() {
        return moduleClassLoaderFactory;
    }

    ClassTransformer getClassFileTransformer() {
        return classFileTransformer;
    }

    Map<String, String> getProperties() {
        return properties;
    }

    PermissionCollection getPermissionCollection() {
        return permissionCollection;
    }

    public Version getVersion() {
        return version;
    }
}
