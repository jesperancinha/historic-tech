SHELL := /bin/bash

SETUP := export SDKMAN_DIR="$(HOME)/.sdkman"; \
         	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
         	source "$(HOME)/.sdkman/bin/sdkman-init.sh"
first-install:
	$(SETUP); \
	sdk install java 21.0.1.crac-zulu
jdk-setup:
	sudo chown root:root $(JAVA_HOME)/lib/criu
	sudo chmod u+s $(JAVA_HOME)/lib/criu
b: build-gradle
build-gradle:
	$(SETUP); \
	./gradlew build