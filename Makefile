b: build
build:
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-kafka && gradle build -x test
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-rabbitmq && gradle build -x test
	cd you-tube-sessions/long-videos/spot-on && gradle build -x test
	cd you-tube-sessions/overlay-shorts/jeorg-overlay-group-1 && gradle build -x test
	cd you-tube-sessions/overlay-shorts/jeorg-overlays-group-1-spring && gradle build -x test
