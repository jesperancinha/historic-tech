b: build
build: ./streams*
		for d in $^ ; do \
  			cd $${d}; \
  			make b; \
  			cd ..; \
		done
