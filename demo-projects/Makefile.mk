include ../../Makefile.mk

build-gradle-report:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --no-validate-url; \
	./gradlew clean build test jacocoTestReport -i
build-gradle:
	./gradlew build test
build-wrapper:
	gradle wrapper;
run:
	./gradlew run
