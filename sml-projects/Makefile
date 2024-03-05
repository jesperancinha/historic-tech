install-all:
	wget https://smlnj.org/dist/working/110.99.4/config.tgz
	tar -xzf config.tgz
	config/install.sh
start:
	bin/sml
run-demo-fast:
	echo 'use "factorial-recursive.sml"; factorial(5);' | bin/sml
	echo 'use "factorial-recursive-tco.sml"; factorial(5);' | bin/sml
run-demo-slow:
	echo 'use "fibonacci-recursive-tco.sml"; fibonacci(5);' | bin/sml
	echo 'use "fibonacci-recursive-tco.sml"; fibonacci(100);' | bin/sml
	echo 'use "fibonacci-recursive.sml"; fibonacci(100);' | bin/sml
