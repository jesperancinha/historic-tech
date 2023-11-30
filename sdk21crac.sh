#!/usr/bin/env sh

echo -e '\033[92mChanging to the first available SDK MAN Java SDK 21 version.\033[0m'
echo -e '\033[91mRemember to run this command like this: . ./sdk21.sh\033[0m'
sdk install java 21.0.1.crac-zulu
sdk use java $(sdk list java | grep installed | grep "| 21.0.1.crac-zulu" |  cut -d'|' -f6- | cut -d' ' -f2-)
echo $JAVA_HOME
sudo chown root:root $JAVA_HOME/lib/criu ; \
sudo chmod u+s $JAVA_HOME/lib/criu