SHELL := /bin/bash

SETUP := export SDKMAN_DIR="$(HOME)/.sdkman"; \
         	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
         	source "$(HOME)/.sdkman/bin/sdkman-init.sh"

compile-only:
	$(SETUP); \
	kotlinc src/main/Calculator.kt -d target
compile-only-jar:
	$(SETUP); \
	kotlinc src/main/Calculator.kt -include-runtime -d target/calculator.jar
install-kotlin:
	$(SETUP); \
	sdk install kotlin
test-jar:
	java -jar target/calculator.jar Add add 1 2
	java -jar target/calculator.jar Add join 1 2
local-pipeline: compile-only compile-only-jar test-jar
