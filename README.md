Java EE 7 - Gradle 2+ - Apache Wicket 7 -Wildfly
==============

Gradle Wildfly EE Wicket 7 Quickstarter
--------------

*Wicket 7*
- Java 7 or 8
- HTML5
- CSS3
- Wicket-Boostrap
- Wicket-Stuff
- with Examples as Showroom included

*JPA 2.1*
- hibernate (default on JBoss/Wildfly)
- eclipselink

*SASS CSS - Compass*
- compiling and checking CSS
- clean and reusable

**Gradle Java EE 7 - Wildfly - Quickstart Project**

*You need wildfly 8.2+ or 9.+ to use Java EE 7 Features out of the box*

default wildfly: 9.0.1.Final

- Java EE 7 Web Project
- Wildfly 8.+ or Wildfly 9.+
- Gradle 2.+ now on 2.7
- PowerShell Deployment

Setup your favorite appaserver on gradle.properties


Gradle - basic commands
--------------
gradle wrapper (once to setup your wrapper)

*startup your gradle.properties configured local wildfly server*

Now you can use:

gradlew tasks

gradlew war -i

gradlew clean test -i
gradlew clean test war -i

gradlew clean build -i
gradlew clean war -i

gradlew dependencies

*the first use you should start a download of gradle-version-bin.zip*

Please install your favorite IDE (Eclipse, IntelliJ, Netbeans) 
and import this project as Gradle Project.
**You have to use the build.gradle to import in IDE.**
You will find a Gradle View in your IDE to see all tasks.

*Powershell: .\gradlew tasks*

** TBD / TODO **

*should be all work without a local appserver on buildserver or host - by CLI*

- Gradle Test should use a port-offset=20000
- Gradle deployment tasks - see ./gradle/plugin-wildfly.gradle
- Gradle server command tasks (restart, reload, start, stop)
- Gradle CI build (Jenkins) to test

	some code examples inside to test

Wildfly
--------------

For now Wildfly have to be running on localhost with port-offset 10000.
So please setup this as VM Argument: '-Djboss.socket.binding.port-offset=10000' 

Tests on build will fail if your wildfly is not reachable or running.
Take a look in folder scripts file unix-commands.txt on Mac and Linux/Unix 
or use PowerShell Scripts on Windows.

Database 
--------------

This project needs a SQL Database.
You will find the commands for MySQL in folder scripts.


CI - Jenkins
--------------

This project is configured for Jenkins and its Plugins to show

* PMD
* Checkstyle
* Jacoco
* FindBugs
* Codenarc
* jDepend
* JUnit 4


IDE
--------------

*It's all tested on Windows 7, 8 and 10 and Mac OS X 10.9*

* Eclipse EE 4.4 and 4.5 - Gradle IDE Pack 3.6.x by Eclipse Marketplace
* STS 3.6.3 - Gradle IDE Pack 3.6.x by Eclipse Marketplace
* IntelliJ 14.1 - Gradle aboard
* Mobile Version with JQuery Mobile 1.4 aboard

If you get it right, you can take a look on

*Wicket 7*

* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/
* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/mobile
* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/responsive
* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/basecss
* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/components

*Primefaces*

* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/securities.xhtml

*JSF - Java EE 7*

* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/start.xhtml
* http://localhost:18080/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT/index.xhtml


Links - Documentation used
--------------
- http://www.adam-bien.com/
- https://bintray.com/fwelland/FredsStuff/gradle-wildfly-plugin
- http://stupidfredtricks.blogspot.de/2014/08/a-simple-gradle-plugin-for-wildfly-and.html
- https://docs.jboss.org/author/display/WFLY9/Documentation
- https://developer.jboss.org/wiki/CheatsheetTestingWithGradleArquillianAndWildFly
- https://developer.jboss.org/wiki/HowToUseEclipseLinkWithAS7
- https://github.com/arquillian/continuous-enterprise-development/
- https://docs.jboss.org/author/display/WFLY9/JPA+Reference+Guide#JPAReferenceGuide-UsingEclipseLink
- http://middlewaremagic.com/jboss/?p=350
- http://www.primefaces.org/showcase/
- http://mvnrepository.com/
- http://groovy-lang.org/documentation.html
- http://gradle.org/docs/current/userguide/userguide.html
- http://git-scm.com/docs/
- http://google-styleguide.googlecode.com/svn/trunk/javaguide.html
- https://developer.jboss.org/wiki/ConfiguringMultipleJBossInstancesOnOneMachine
- https://www.jfrog.com/confluence/display/RTF/Working+with+Gradle

Help?
--------------
Yes, please.
Make your branch or fork me and send your pull requests!

License
--------------
**GNU GPLv3 Copyright**
*by Klaus Landsdorf - Lohne (Olb) - Germany*
