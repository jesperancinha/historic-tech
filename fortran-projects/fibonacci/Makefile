install-all:
	sudo apt-get install gfortran
run-demo-fast:
	gfortran -c fibonacci-iterative.f90
	gfortran fibonacci-iterative.f90
	./a.out
	rm a.out
	gfortran -c fibonacci-tail-recursive-tco.f90
	gfortran fibonacci-tail-recursive-tco.f90
	./a.out
	rm a.out
run-demo: run-demo-fast
	gfortran -o fibonacci-recursive fibonacci-recursive.f90
	./fibonacci-recursive
