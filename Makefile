b: build
build:
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-kafka && gradle build -x test
	cd talks/talk-10-kotlin-streams-good-bad/streams-gb-rabbitmq && gradle build -x test
	cd you-tube-sessions/spot-on && gradle build -x test
