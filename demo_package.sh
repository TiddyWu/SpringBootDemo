#!/bin/bash

## This shell script package the spring-boot maven program and scp the target to the remote server
## parameter 1: AWS crenditial key
## parameter 1: remote server address

#My typical usange is: ./demo_package.sh ~/.ssh/JiaweiwuEC2KeyPair.pem ec2-user@18.139.143.28:/home/ec2-user


red=`tput setaf 1`
green=`tput setaf 2`
reset=`tput sgr0`
bold=`tput bold`
echo "${bold}Running mvn package${reset}"

# This is Intellij maven build command, this is tricy I don't know what is happening here...
/Library/Java/JavaVirtualMachines/amazon-corretto-8.jdk/Contents/Home/bin/java -Dmaven.multiModuleProjectDirectory=/Users/jiaweiwu/workplace-insensitive/demo "-Dmaven.home=/Users/jiaweiwu/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/191.7479.19/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3" "-Dclassworlds.conf=/Users/jiaweiwu/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/191.7479.19/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/bin/m2.conf" "-javaagent:/Users/jiaweiwu/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/191.7479.19/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=59630:/Users/jiaweiwu/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/191.7479.19/IntelliJ IDEA.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath "/Users/jiaweiwu/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/191.7479.19/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/boot/plexus-classworlds-2.5.2.jar" org.codehaus.classworlds.Launcher -Didea.version2019.1.3 package

if [ $? == 0 ]
then
    echo "${bold}${green}--------------------------------${reset}"
    echo "${bold}${green}     maven package success      ${reset}"
    echo "${bold}${green}--------------------------------${reset}"
else
    echo "${bold}${red}--------------------------------${reset}"
    echo "${bold}${red}        maven package fail      ${reset}"
    echo "${bold}${red}--------------------------------${reset}"
    exit 1
fi

echo "${bold}scp package to the server${reset}"

scp -i ${1} -r target ${2}
