#!/usr/bin/env bash

echo -e '\e[31mWARNING!!\e[0m'
echo -e '\e[32mCarefull when using this script. It will use the rm -rf on your /usr/lib/jvm/java* folders\e[0m'
echo -e "This will in order words force the removal of java versions! \e[0m"
read -p "Are you sure? (Yy/Nn)" -n 1 -r
if [[ $REPLY =~ ^[Yy]$ ]]; then
  sudo update-alternatives --remove java /usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java
  sudo update-alternatives --remove java /usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/javac
  sudo update-alternatives --remove java /usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/javaw
  sudo update-alternatives --remove java /usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/javaws
  sudo rm -rf /usr/lib/jvm/java*
fi
