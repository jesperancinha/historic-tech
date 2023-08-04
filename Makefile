SHELL := /bin/bash
GRADLE_VERSION ?= 8.2.1
PARENT_MODULES := talks you-tube-sessions
MODULE_LOCATIONS := talks/talk-10-kotlin-streams-good-bad/streams-gb-kafka \
					talks/talk-10-kotlin-streams-good-bad/streams-gb-rabbitmq \
					talks/talk-10-kotlin-streams-good-bad/streams-gb-single \
					you-tube-sessions/long-videos/spot-on \
					you-tube-sessions/long-videos/streams \
					you-tube-sessions/long-videos/library-channels \
					you-tube-sessions/long-videos/drinks-manager \
					you-tube-sessions/long-videos/kotlin-keywords \
					you-tube-sessions/long-videos/cooldrinks-juice-factory \
					you-tube-sessions/overlay-shorts/jeorg-overlay-group-1 \
					you-tube-sessions/overlay-shorts/jeorg-overlays-group-1-spring \
					you-tube-sessions/overlay-shorts/coroutines-demo \
					experiments/coroutine-builders \
					experiments/annotations-demo
b: buildw
build: buildw
buildw:
	@for location in $(MODULE_LOCATIONS); do \
  		export CURRENT=$(shell pwd); \
  		echo "Building $$location..."; \
		cd $$location; \
		gradle build -x test; \
		cd $$CURRENT; \
	done
upgrade:
	@for location in $(MODULE_LOCATIONS); do \
  		export CURRENT=$(shell pwd); \
  		echo "Upgrading $$location..."; \
		cd $$location; \
		gradle wrapper --gradle-version $(GRADLE_VERSION); \
		cd $$CURRENT; \
	done
build-local: build-talks build-youtube
build-talks: ./talk*
		for d in $^ ; do \
  			cd $${d}; \
  			make b; \
  			cd ..; \
		done
build-youtube: ./you-tube*
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
upgrade-gradle:
	sudo apt upgrade
	sudo apt update
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
	make upgrade
install-linux:
	sudo apt-get install jq
	sudo apt-get install curl
	curl https://services.gradle.org/versions/current
