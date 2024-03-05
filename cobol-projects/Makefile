install-all:
	#sudo apt-get install open-cobol
	sudo apt-get install gnucobol
run-example-fast:
	cobc -x factorial-iterative.cob
	./factorial-iterative
	cobc -x -free factorial-recursive.cob
	./factorial-recursive
	cobc -x -free fibonacci-iterative.cob
	./fibonacci-iterative
	cobc -x -free fibonacci-recursive.cob
	./fibonacci-recursive
run-demo: run-cobol-demo
run-cobol-demo: run-example-fast
