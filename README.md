This is the JBossModulesAT(JBoss GSoC 2018) project.

JBossModulesAT is an implementation of the AT Structures for the JBoss-Modules testsuite.

in order to describe how you run JBModulesAT with the different versions of jboss-modules
e.g. For 1.x
       -Download jboss-modules sources from [https://github.com/jboss-modules/jboss-modules/tree/1.x](https://github.com/jboss-modules/jboss-modules/tree/1.x)
       - Build the jboss-module sources with the command mvn clean install -DskipTests
       - Set JBOSS_VERSION env variable to 1.9.0.Final-SNAPSHOT
       - Run JBModulesAT using the command mvn clean install -D1.x -s "./example-setting.xml"

     For 1.7
       - Download jboss-modules sources from [https://github.com/jboss-modules/jboss-modules/tree/1.7](https://github.com/jboss-modules/jboss-modules/tree/1.7)
       - Build the jboss-module sources with the command >>>  mvn clean install -DskipTests
       - Set JBOSS_VERSION env variable to >>> 1.7.1.Final-SNAPSHOT
       - Run JBModulesAT using the command >> mvn clean install -D1.x -Djboss.modules.system.pkgs=javax.naming -s "./example-setting.xml" 
        
when testing the sources of jboss-modules before 1.8.0 version we should add -Djboss.modules.system.pkgs=javax.naming

e.g. mvn clean install -D1.x -Djboss.modules.system.pkgs=javax.naming
     
     For 1.8
       - Download jboss-modules sources from [https://github.com/jboss-modules/jboss-modules/tree/1.8](https://github.com/jboss-modules/jboss-modules/tree/1.8)
       - Build the jboss-module sources with the command >>>  mvn clean install -DskipTests
       - Set JBOSS_VERSION env variable to >>> 1.8.6.Final-SNAPSHOT
       - Run JBModulesAT using the command >> mvn clean install -D1.x  -s "./example-setting.xml" 



## License


Code distributed under [ASL 2.0](LICENSE.TXT),[XPP3](XPP3-LICENSE.TXT) (licenses of the JBoss-Module test sources) and [GNU Lesser General Public License Version 2.1](http://www.gnu.org/licenses/lgpl-2.1-standalone.html) (for the repo)
