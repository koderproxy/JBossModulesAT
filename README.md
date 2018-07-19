This is the JBossModulesAT(JBoss GSoC 2018) project.

JBossModulesAT is an implementation of the AT Structures for the JBoss-Modules testsuite.



## when testing the sources of jboss-modules before 1.8.0 version we should add -Djboss.modules.system.pkgs=javax.naming

e.g. mvn clean install -D1.x -Djboss.modules.system.pkgs=javax.naming


## License


Code distributed under [ASL 2.0](LICENSE.TXT),[XPP3](XPP3-LICENSE.TXT) (licenses of the JBoss-Module test sources) and [GNU Lesser General Public License Version 2.1](http://www.gnu.org/licenses/lgpl-2.1-standalone.html) (for the repo)
