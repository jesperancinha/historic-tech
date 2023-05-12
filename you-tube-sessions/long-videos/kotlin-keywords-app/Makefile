include ../../../Makefile

b: build-gradle-report
build-gradle:
	./gradlew build test
build-gradle-report:
	gradle wrapper --gradle-version $(GRADLE_VERSION);
build-wrapper:
	gradle wrapper --gradle-version $(GRADLE_VERSION);
