include ../Makefile.mk

b?: build
build:
	for d in *; do \
		if [ -d $$d ]; then \
		  if [ $$d != gradle ]; then \
			cd $${d}; \
			make b; \
			cd ..; \
		  fi \
		fi \
	done
