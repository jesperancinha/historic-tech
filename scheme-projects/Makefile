install-all:
	sudo apt-get install guile-3.0
start:
	guile
run-demo: run-demo-fast run-demo-slow
run-demo-fast:
	guile -s factorial-recursive.scm -o factorial-recursive
	guile -s factorial-recursive-tco.scm -o factorial-recursive-tco
	guile -s fibonacci-iterative.scm -o fibonacci-iterative
	guile -s fibonacci-recursive-tco.scm -o fibonacci-recursive-tco
run-demo-slow:
	guile -s fibonacci-recursive.scm -o fibonacci-recursive

