include ../../Makefile.mk

b: build-gradle-report
build-gradle: build-dll
	./gradlew build test
build-gradle-report: build-dll
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --no-validate-url; \
	./gradlew clean build test jacocoTestReport -i
build-wrapper:
	gradle wrapper;
build-dll:
	if [ -f "src/main/resources/DozenCount.dll" ]; then rm src/main/resources/DozenCount.dll; fi
	if [ -f "src/main/resources/dozenCount.dll" ]; then rm src/main/resources/dozenCount.dll; fi
	if [ -f "DozenCount.o" ]; then rm DozenCount.o; fi
	gcc -c DozenCount.c
	gcc -o src/main/resources/DozenCount.dll --shared DozenCount.o
