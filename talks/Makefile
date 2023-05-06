b: build
build: ./talk*
		for d in $^ ; do \
  			cd $${d}; \
  			make b; \
  			cd ..; \
		done
