install-all:
	#sudo apt-get install open-cobol
	sudo apt-get install gnucobol
run-example:
	cobc -x factorial-iterative.cob
	./factorial-iterative
	cobc -x -free factorial-recursive.cob
	./factorial-recursive
