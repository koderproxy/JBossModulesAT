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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
final class Utils {

    static final String MODULE_VERSION = "Module-Version";
    static final String DEPENDENCIES = "Dependencies";
    static final String EXPORT = "export";
    static final String OPTIONAL = "optional";
    static final String SERVICES = "services";
    static final String MODULES_DIR = "modules";
    static final String MODULE_FILE = "module.xml";
    static final String[] NO_STRINGS = new String[0];

    private Utils() {
        // forbidden instantiation
    }

    static final ModuleLoader JDK_MODULE_LOADER = new ModuleLoader(JDKModuleFinder.getInstance());

    static void copy(final InputStream in, final OutputStream out) throws IOException {
        byte[] buf = new byte[16384];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.flush();
    }

}
