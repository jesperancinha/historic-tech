include ../../Makefile.mk


b: build-gradle-report
build-gradle:
	./gradlew build test
build-gradle-report:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --no-validate-url; \
#	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	gradle wrapper;
run:
	./gradlew run
