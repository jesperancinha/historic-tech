install-all:
	sudo apt install erlang
start:
	erl
run-demo-fast:
	echo 'c(factorial_recursive_tco). factorial_recursive_tco:factorial(5).' | erl
	echo 'c(fibonacci_recursive). fibonacci_recursive:fib(10).' | erl
	echo 'c(fibonacci_recursive_tco). fibonacci_recursive_tco:fib(100).' | erl
run-demo-slow:
	echo 'c(fibonacci_recursive). fibonacci_recursive:fib(100).' | erl
run-demo: run-demo-fast run-demo-slow
