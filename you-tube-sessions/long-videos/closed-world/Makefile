SHELL := /bin/bash

SETUP := export SDKMAN_DIR="$(HOME)/.sdkman"; \
         	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
         	source "$(HOME)/.sdkman/bin/sdkman-init.sh"
# Java only builds
compile-only:
	$(SETUP); \
	javac src/main/CalculatorJava.java -d target
compile-native-only: compile-only
	$(SETUP); \
	cd target; \
	native-image --no-fallback CalculatorJava
compile-only-jar:
	$(SETUP); \
	cd src/main ; \
	javac CalculatorJava.java -d target/calculatorjava.jar
inspect-classes:
	$(SETUP); \
	cd target; \
	java -agentlib:native-image-agent=config-output-dir=META-INF/native-image CalculatorJava Add add 1 2
test-native:
	target/calculatorjava Add add 1 2
	target/calculatorjava Add join 1 2
test-jar:
	$(SETUP); \
	java -jar target/calculatorjava.jar Add add 1 2; \
	java -jar target/calculatorjava.jar Add join 1 2
test-class:
	$(SETUP); \
	java -classpath target/. CalculatorJava Add add 1 2
	java -classpath target/. CalculatorJava Add join 1 2
local-pipeline: compile-only compile-only-jar test-jar

# Kotlin only builds
compile-kotlin-only:
	$(SETUP); \
	kotlinc src/main/Calculator.kt -d target
compile-native-kotlin-only: compile-kotlin-only
	$(SETUP); \
	cd target; \
	native-image --no-fallback Calculator
compile-kotlin-only-jar:
	$(SETUP); \
	kotlinc src/main/Calculator.kt -include-runtime -d target/calculator.jar
install-kotlin:
	$(SETUP); \
	sdk install kotlin
inspect-kotlin-classes:
	$(SETUP); \
	cd target; \
	java -agentlib:native-image-agent=config-output-dir=META-INF/native-image Calculator Add add 1 2
test-kotlin-jar:
	java -jar target/calculator.jar Add add 1 2
	java -jar target/calculator.jar Add join 1 2
test-kotlin-class:
	java -classpath target/. Calculator Add add 1 2
	java -classpath target/. Calculator Add join 1 2
local-kotlin-pipeline: compile-kotlin-only compile-kotlin-only-jar test-kotlin-jar
