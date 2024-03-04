include ../Makefile.mk

run-demo:
	sbcl --noinform \
					--eval '(require "asdf")' \
					--eval '(load (merge-pathnames "~/quicklisp/setup.lisp" (uiop:getcwd)))' \
					--eval "(ql:quickload '("str" "cl-ppcre" "alexandria" "cl-project" "rove"))" \
					--eval '(asdf:load-asd (merge-pathnames "tailrec-lisp.asd" (uiop:getcwd)))' \
					--eval '(asdf:load-system :tailrec-lisp)' \
					--eval '(asdf:test-system :tailrec-lisp)' \
					--eval '(tailrec-lisp:two-power-of 3)' \
					--eval '(format t "Factorial of 5 is ~a" (tailrec-lisp:factorial 5))' \
   					--eval '(format t "Factorial of 5 is ~a" (tailrec-lisp:factorial-iter 5))' \
   					--eval '(format t "Fibonacci of 100 is ~a" (tailrec-lisp:fibonacci-iterative 100))' \
   					--eval '(quit)'
