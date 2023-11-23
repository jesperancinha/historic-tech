SHELL := /bin/bash

SETUP := export SDKMAN_DIR="$(HOME)/.sdkman"; \
         	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
         	source "$(HOME)/.sdkman/bin/sdkman-init.sh"

FILENAME ?= "MegaClass.kt"

b: make-class
	$(SETUP); \
	gradle build; \
	gradle test
make-class:
	echo "package org.jesperancinha.k2" > $(FILENAME)
	echo "" >> $(FILENAME)
	echo "class MegaClass {" >> $(FILENAME)
	echo "" >> $(FILENAME)
	echo "    companion object {" >> $(FILENAME)
	echo "" >> $(FILENAME)
	echo "        fun run(){" >> $(FILENAME)
	echo "            println(\"Lines of code\")" >> $(FILENAME)
	echo "        }" >> $(FILENAME)
	echo "" >> $(FILENAME)
	echo "    }" >> $(FILENAME)
	echo "}" >> $(FILENAME)
	mv $(FILENAME) src/main/kotlin/org/jesperancinha/k2/$(FILENAME)