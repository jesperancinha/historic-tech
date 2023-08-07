#!/bin/bash
echo "Run Terminal"
echo "Running installation specific for YouTubers"
sudo apt-get upgrade -y
sudo apt-get update -y --fix-missing
sudo apt-get install vim wget jq curl -y
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo dpkg -i google-chrome-stable_current_amd64.deb
echo "Choose SDDM!"
sudo apt-get install kde-full -y
sudo apt-get install simplescreenrecorder webcamoid kamoso cheese -y
sudo apt-get update
sudo apt-get install pitivi openshot-qt vlc -y
sudo snap install qprompt
sudo apt install flatpak -y
sudo add-apt-repository ppa:kdenlive/kdenlive-stable -y
sudo apt update -y
sudo apt install kdenlive -y
