# JBTAT
### JBOSS THREADS ADDITIONAL TESTSUITE USES THE JBOSS TESTSUITE TO DEVELOP TESTS AGAINST INFINITE NUMBER OF SOFTWARE PROJECT VERSIONS

### A PROJECT UNDER THE ΙΔΕΑ STATEMENT

INNOVATION
-----------------------------------
The innovative part of JBTAT (Known as EAT for the JBOSS Servers) is creating the test once and testing with any version of the tested software. It may be firstly applied for the JBOSS Servers, but, in general, a similar structure, can be used for creating tests about any software with multiple versions or for multiple software programs that have a part of the testsuite in common.


Testing the Master JBoss Threads Branch
---------------------------------------
1. Make sure that JBOSS_VERSION environment variable is set with the version of your JBoss Threads build.
2. Build and run the jboss threads additional testsuite activating the master profile (mvn clean install -Dmaster).


Testing the 2.2 JBoss Threads Branch
---------------------------------------
1. Make sure that JBOSS_VERSION environment variable is set with the version of your JBoss Threads build.
2. Build and run the jboss threads additional testsuite activating the 2.2 profile (mvn clean install -D2.2).


Testing the 2.1 JBoss Threads Branch
---------------------------------------
1. Make sure that JBOSS_VERSION environment variable is set with the version of your JBoss Threads build.
2. Build and run the jboss threads additional testsuite activating the 2.1 profile (mvn clean install -D2.1).


Testing the 1.0.0.GA JBoss Threads Release
-------------------------------------------
1. Make sure that JBOSS_VERSION environment variable is set with the version of your JBoss Threads build.
2. Build and run the jboss threads additional testsuite activating the 1.0.0.GA profile (mvn clean install -D1.0.0.GA).


# License 
* [GNU Lesser General Public License Version 2.1](http://www.gnu.org/licenses/lgpl-2.1-standalone.html)
