install-all:
	#sudo apt-get install open-cobol
	sudo apt-get install gnucobol
run-example:
	cobc -x factorial-recursive.cob
	./factorial
	cobc -x -free factorial-recursive-tco.cob
	./factorial-recursive
