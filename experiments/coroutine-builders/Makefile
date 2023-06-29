SHELL := /bin/bash
GRADLE_VERSION ?= 8.1.1

b: build
build:
	gradle build -x test
run:
	gradle run
run-java:
	./gradlew runWithJavaExec