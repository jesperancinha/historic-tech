# asnsei-the-right-waf

## Install LISP

```shell
apt-get install sbcl
apt-get install rlwrap
apt-get install cl-quicklisp
```

```lisp
(load "/usr/share/common-lisp/source/quicklisp/quicklisp.lisp")
(quicklisp-quickstart:install)
(ql:quickload "str")
(ql:quickload '("str" "cl-ppcre" "alexandria", "cl-project", "rove"))
(ql:quickload "cl-project")
(ql:quickload "rove")
```

## After install

```common lisp
(ql:quickload "str")
(ql:quickload "cl-project")
(ql:quickload "rove")
```

## Project creation

```common lisp
(cl-project:make-project #P"./tailrec-lisp")
(in-package :tailrec-lisp)
(asdf:test-system :tailrec-lisp)
(pushnew "./tailrec-lisp" asdf:*central-registry* :test #'equal)
(setf *print-case* :downcase)
(require "asdf")
(asdf:load-asd (merge-pathnames "tailrec-lisp/tailrec-lisp.asd" (uiop:getcwd)))
```

## Loading

```common lisp
(require "asdf")
(load (merge-pathnames "~/quicklisp/setup.lisp" (uiop:getcwd)))
(ql:quickload '("str" "cl-ppcre" "alexandria" "cl-project" "rove"))
(asdf:load-asd (merge-pathnames "tailrec-lisp/tailrec-lisp.asd" (uiop:getcwd)))
(asdf:test-system :tailrec-lisp)
```

## Others

```common lisp
(load "/home/jesperancinha/quicklisp/setup.lisp")
(asdf:load-asd #P"./tailrec-lisp.asd")
```

## Resources

-   [The Common Lisp Cookbook – Getting started with Common Lisp](https://lispcookbook.github.io/cl-cookbook/getting-started.html)