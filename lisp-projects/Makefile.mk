install-all: install-linux issue-setup-commands

install-linux:
	sudo apt-get install sbcl
	sudo apt-get install rlwrap
	sudo apt-get install cl-quicklisp
start:
	rlwrap sbcl
start-sbcl:
	sbcl --load /usr/share/common-lisp/source/quicklisp/quicklisp.lisp
issue-setup-commands:
	sbcl --noinform --eval '(load "/usr/share/common-lisp/source/quicklisp/quicklisp.lisp")' \
					--eval '(quicklisp-quickstart:install)' \
					--eval '(quit)'
