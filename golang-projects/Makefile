SHELL := /bin/bash

install-go:
	rm go*.tar.gz*
	wget https://go.dev/dl/go1.21.0.linux-amd64.tar.gz
	sudo rm -rf /usr/local/go && sudo tar -C /usr/local -xzf go1.21.0.linux-amd64.tar.gz
	export PATH=$$PATH:/usr/local/go/bin
add-export-to-zsh:
	echo -n "export PATH=" >> ~/.zshrc && printf %s "$$" >> ~/.zshrc && echo "PATH:/usr/local/go/bin" >> ~/.zshrc
