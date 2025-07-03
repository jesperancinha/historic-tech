include Makefile.mk

PARENT_MODULES := talks demo-projects-sessions
MODULE_LOCATIONS := talks/talk-10-kotlin-streams-good-bad/streams-gb-kafka \
					talks/talk-10-kotlin-streams-good-bad/streams-gb-rabbitmq \
					talks/talk-10-kotlin-streams-good-bad/streams-gb-single \
					demo-projects/k2-compiler \
					demo-projects/closed-world \
					demo-projects/spot-on \
					demo-projects/streams \
					demo-projects/library-channels \
					demo-projects/drinks-manager \
					demo-projects/kotlin-keywords \
					demo-projects/cooldrinks-juice-factory \
					demo-projects/jeorg-overlay-group-1 \
					demo-projects/jeorg-overlays-group-1-spring \
					demo-projects/coroutines-demo \
					demo-projects/guessing-retries \
					experiments/coroutine-builders \
					experiments/annotations-demo \
					experiments/generics-demo \
					experiments/channels \
					experiments/koin-example \
					experiments/supabase-demo \
					experiments/dokka-multilevel
b: buildw
build: buildw
buildw:
	@set -e; \
	for location in $(MODULE_LOCATIONS); do \
		export CURRENT=$$(pwd); \
		echo "Building $$location..."; \
		cd $$location; \
		pwd; \
		make b; \
		gradle -x test; \
		cd $$CURRENT; \
	done
upgrade:
	@for location in $(MODULE_LOCATIONS); do \
  		export CURRENT=$(shell pwd); \
  		echo "Upgrading $$location..."; \
		cd $$location; \
		gradle wrapper; \
		cd $$CURRENT; \
	done
build-local: build-talks build-youtube
build-talks: ./talk*
		for d in $^ ; do \
  			cd $${d}; \
  			make b; \
  			cd ..; \
		done
build-youtube: ./demo-projects*
		for d in $^ ; do \
			cd $${d}; \
			make b; \
			cd ..; \
		done
local-pipeline:
	@for module in $(PARENT_MODULES); do \
  		cd $$module; \
  		make b; \
  		cd ..; \
	done
upgrade-gradle: upgrade-system upgrade-sdk-man upgrade
upgrade-system:
	sudo apt upgrade
	sudo apt update
upgrade-sdk-man:
	export SDKMAN_DIR="$(HOME)/.sdkman"; \
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
	source "$(HOME)/.sdkman/bin/sdkman-init.sh"; \
	sdk update; \
	gradleOnlineVersion=$(shell curl -s https://services.gradle.org/versions/current | jq .version | xargs -I {} echo {}); \
	if [[ -z "$$gradleOnlineVersion" ]]; then \
		sdk install gradle $(GRADLE_VERSION); \
		sdk use gradle $(GRADLE_VERSION); \
	else \
		sdk install gradle $$gradleOnlineVersion; \
		sdk use gradle $$gradleOnlineVersion; \
		export GRADLE_VERSION=$$gradleOnlineVersion; \
	fi;
install-linux:
	sudo apt-get install jq
	sudo apt-get install curl
	curl https://services.gradle.org/versions/current
sdk-man-install:
	curl -s "https://get.sdkman.io" | bash; \
	source "$(HOME)/.sdkman/bin/sdkman-init.sh"; \
	export SDKMAN_DIR="$(HOME)/.sdkman"; \
    [[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
    sdk install kotlin
install:
	sdk install kotlin
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-gradle-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/gradleUpdatesOne.sh | bash
deps-quick-update: deps-gradle-update deps-plugins-update deps-java-update
update-repo-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/update-all-repo-prs.sh | bash
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash

