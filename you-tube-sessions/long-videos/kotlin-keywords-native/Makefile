include ../../../Makefile
SHELL := /bin/bash

b: build
build: build-jar build-lib-linux
build-jar:
	kotlinc/bin/kotlinc -include-runtime -d TimeDozen.jar *.kt
build-lib-linux:
	gcc dozen.c -o libdozen.so -shared -fPIC -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/linux -I /usr/lib/x86_64-linux-gnu/jni,
	sudo cp libdozen.so /usr/lib/x86_64-linux-gnu/jni
install:
	export LATEST_KOTLIN=$(shell curl -s https://api.github.com/repos/JetBrains/kotlin/releases/latest | jq .tag_name | xargs -I {} echo {}); \
	export NUMBER_VERSION=$(shell curl -s https://api.github.com/repos/JetBrains/kotlin/releases/latest | jq .tag_name | xargs -I {} echo {} | sed "s|v||g"); \
	echo "Downloading kotlin version $$LATEST_KOTLIN-$$NUMBER_VERSION from https://github.com/JetBrains/kotlin/releases/download/$$LATEST_KOTLIN/kotlin-compiler"; \
	curl -SL https://github.com/JetBrains/kotlin/releases/download/$$LATEST_KOTLIN/kotlin-compiler-$$NUMBER_VERSION.zip -o kotlin-compiler.zip; \
