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
run-demo-slow:
	gfortran -o fibonacci-recursive fibonacci-recursive.f90
	./fibonacci-recursive
run-demo: run-demo-fast
run-1950s:
	gfortran -o fibonacci50s fibonacci50s.f90
	./fibonacci50s
	gfortran -o fibonacci50s-recursive fibonacci50s-recursive.f90
	./fibonacci50s-recursive

