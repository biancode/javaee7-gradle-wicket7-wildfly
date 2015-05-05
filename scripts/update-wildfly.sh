#!/bin/bash

cd ~/IdeaProjects/javaee7-gradle-wicket7-wildfly

~/appserver/wildfly-9.0.0.Beta2/bin/jboss-cli.sh --controller=localhost:19990 --connect --command='deploy --force build/libs/javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT.war'

~/appserver/wildfly-9.0.0.Beta2/bin/jboss-cli.sh --controller=localhost:19990 --connect --command='/:reload'