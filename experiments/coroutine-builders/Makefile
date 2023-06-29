SHELL := /bin/bash
GRADLE_VERSION ?= 8.1.1

b: buildw
buildw:
	gradle build -x test
run:
	gradle run
run-java:
	./gradlew runWithJavaExec

run-all: run run-java
