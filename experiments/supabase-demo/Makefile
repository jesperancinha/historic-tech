SHELL := /bin/bash

b: build-gradle
wrapper-gradle:
	gradle wrapper
build-gradle:
	gradle build
first-install:
	git clone --depth 1 https://github.com/supabase/supabase
install-docker:
	cd supabase/docker; \
	cp .env.example .env
start-docker:
	cd supabase/docker; \
	docker compose pull; \
	docker compose up -d
stop-docker:
	cd supabase/docker; \
	docker compose down;
clean:
	if [ -d supabase ]; then \
	   rm -r supabase; \
	fi
