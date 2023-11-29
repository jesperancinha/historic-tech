include ../../Makefile

b: build-gradle-report
build-gradle:
	./gradlew build test
build-gradle-report:
	gradle wrapper --gradle-version $(GRADLE_VERSION);
	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	gradle wrapper --gradle-version $(GRADLE_VERSION);
