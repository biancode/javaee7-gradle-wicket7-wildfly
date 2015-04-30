#############################################################################
#
# GNU GPLv3 Copyright by Klaus Landsdorf - Lohne (Olb) - Germany
#
# http://bianco-royal.com/
#
#############################################################################

# $githubBranch="-biancode"
# $githubBranch="-master"
$githubBranch=""

$ipaddress="localhost"
$port="19990"

# $wildflyVersion="8.2.0.Final"
# $wildflyVersion="9.0.0.Alpha1"
$wildflyVersion="9.0.0.Beta2"
$wildflyServer = "D:\appserver\wildfly\wildfly-" + $wildflyVersion

$warPath = "D:\github\javaee7-gradle-wicket7-wildfly" + $githubBranch + "\build\libs"
$warFile = "javaee7-gradle-wicket7-wildfly-1.0.0-SNAPSHOT.war"
$targetRollout = "D:\github\javaee7-gradle-wicket7-wildfly" + $githubBranch + "\scripts"

############################################################################# 
Test-Path $targetRollout
cd $targetRollout
dir
Test-Path $wildflyServer

$command = $wildflyServer + "\bin\jboss-cli.bat --controller=" + $ipaddress + ":" + $port + " --connect --command='undeploy " + $warFile + "'"

echo($command)

iex $command

echo("deploy off " + $warFile + " on " + $wildflyServer + " done")

$command = $wildflyServer + "\bin\jboss-cli.bat --controller=" + $ipaddress + ":" + $port + " --connect --command='/:reload'"
echo($command)
iex $command

echo("restart " + $wildflyServer + " done")
