include ../Makefile.mk

run-demo-fast:
	lein run -m fibonacci.core/calculate-factorial 5
	lein run -m fibonacci.core/calculate-all-fibonacci 100
	lein run -m fibonacci.core/calculate-all-fibonacci 1000
run-demo-slow:
	lein run -m fibonacci.core/calculate-factorial 5
	lein run -m fibonacci.core/calculate-all-slow-single-fibonacci 100
	lein run -m fibonacci.core/calculate-all-slow-fibonacci 100
run-demo: run-demo-fast run-demo-slow