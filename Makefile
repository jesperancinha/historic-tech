SHELL := /bin/bash
GRADLE_VERSION := 8.0.2

b: build
build:
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-kafka && gradle build -x test
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-rabbitmq && gradle build -x test
	cd you-tube-sessions/long-videos/spot-on && gradle build -x test
	cd you-tube-sessions/long-videos/kotlin-keywords && gradle build -x test
	cd you-tube-sessions/overlay-shorts/jeorg-overlay-group-1 && gradle build -x test
	cd you-tube-sessions/overlay-shorts/jeorg-overlays-group-1-spring && gradle build -x test
	cd you-tube-sessions/overlay-shorts/coroutines-demo && gradle build -x test
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
	cd you-tube-sessions/long-videos/spot-on && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/long-videos/kotlin-keywords && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/overlay-shorts/jeorg-overlay-group-1 && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/overlay-shorts/jeorg-overlays-group-1-spring && gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd you-tube-sessions/overlay-shorts/coroutines-demo && gradle wrapper --gradle-version $(GRADLE_VERSION)
upgrade-gradle:
	sudo apt upgrade
	sudo apt update
	export SDKMAN_DIR="$(HOME)/.sdkman"
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk update
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk install gradle $(GRADLE_VERSION)
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk use gradle $(GRADLE_VERSION)
