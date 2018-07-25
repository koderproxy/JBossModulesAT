This is the JBossModulesAT(JBoss GSoC 2018) project.

JBossModulesAT is an implementation of the AT Structures for the JBoss-Modules testsuite.

in order to describe how you run JBModulesAT with the different versions of jboss-modules

e.g.
 
     For 1.x
       -Download jboss-modules sources from https://github.com/jboss-modules/jboss-modules/tree/1.x
       - Build the jboss-module sources with the command mvn clean install -DskipTests -s "./example-setting.xml"
       - Set JBOSS_VERSION env variable to the version of the current 1.x branch (e.g 1.9.0.Final-SNAPSHOT)
       - Run JBModulesAT using the command mvn clean install -D1.x -s "./example-setting.xml"
    
     For 1.8
       - Download jboss-modules sources from https://github.com/jboss-modules/jboss-modules/tree/1.8
       - Build the jboss-module sources with the command mvn clean install -DskipTests -s "./example-setting.xml"
       - Set JBOSS_VERSION env variable to the version of the current 1.8 branch (e.g 1.8.6.Final-SNAPSHOT)
       - Run JBModulesAT using the command >>> mvn clean install -D1.x  -s "./example-setting.xml"


     For 1.7
       - Download jboss-modules sources from https://github.com/jboss-modules/jboss-modules/tree/1.7
       - Build the jboss-module sources with the command mvn clean install -DskipTests -s "./example-setting.xml"
       - Set JBOSS_VERSION env variable to the version of the current 1.7 branch (e.g 1.7.1.Final-SNAPSHOT)
       - Run JBModulesAT using the command >>> mvn clean install -D1.x -Djboss.modules.system.pkgs=javax.naming -s "./example-setting.xml" 
        
     
     For 1.6
       - Download jboss-modules sources from https://github.com/jboss-modules/jboss-modules/tree/1.6
       - Build the jboss-module sources with the command mvn clean install -DskipTests -s "./example-setting.xml"
       - Set JBOSS_VERSION env variable to the version of the current 1.6 branch (e.g 1.6.5.Final-SNAPSHOT)
       - Run JBModulesAT using the command >>> mvn clean install -D1.x -Djboss.modules.system.pkgs=javax.activation -s "./example-setting.xml" 
        
      

 

## License


Code distributed under [ASL 2.0](LICENSE.TXT),[XPP3](XPP3-LICENSE.TXT) (licenses of the JBoss-Module test sources) and [GNU Lesser General Public License Version 2.1](http://www.gnu.org/licenses/lgpl-2.1-standalone.html) (for the repo)
