SHELL := /bin/bash

b: build
build:
	for d in *; do \
		if [ -d $$d ]; then \
			cd $${d}; \
			make b; \
			cd ..; \
		fi \
	done
