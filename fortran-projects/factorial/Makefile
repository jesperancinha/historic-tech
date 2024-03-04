include ../Makefile.mk

run-demo:
	gfortran -c factorial-iterative.f90
	gfortran factorial-iterative.f90
	./a.out
	rm a.out
	gfortran -c factorial-recursive.f90
	gfortran factorial-recursive.f90
	./a.out
