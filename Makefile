SHELL := /bin/bash
GRADLE_VERSION := 8.1.1

b: build
build:
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-kafka && gradle build -x test
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-rabbitmq && gradle build -x test
	cd you-tube-sessions/jeorg-long-videos/jeorg-spot-on && gradle build -x test
	cd you-tube-sessions/jeorg-long-videos/jeorg-kotlin-keywords && gradle build -x test
	cd you-tube-sessions/jeorg-overlay-shorts/jeorg-overlay-group-1 && gradle build -x test
	cd you-tube-sessions/jeorg-overlay-shorts/jeorg-overlays-group-1-spring && gradle build -x test
	cd you-tube-sessions/jeorg-overlay-shorts/coroutines-demo && gradle build -x test
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
upgrade:
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-kafka && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-rabbitmq && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/jeorg-long-videos/jeorg-spot-on && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/jeorg-long-videos/jeorg-kotlin-keywords && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/jeorg-overlay-shorts/jeorg-overlay-group-1 && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/jeorg-overlay-shorts/jeorg-overlays-group-1-spring && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/jeorg-overlay-shorts/coroutines-demo && gradle wrapper --gradle-version $(GRADLE_VERSION)
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
	fi
install-linux:
	sudo apt-get install jq
	sudo apt-get install curl
	curl https://services.gradle.org/versions/current
