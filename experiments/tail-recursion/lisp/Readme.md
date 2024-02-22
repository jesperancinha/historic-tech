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
(ql:quickload '("str" "cl-ppcre" "alexandria"))
(ql:quickload "cl-project")
(ql:quickload "rove")
```

## Project creation

```lisp
(cl-project:make-project #P"./tailrec-lisp")
(in-package :tailrec-lisp)

```

## Resources

-   [The Common Lisp Cookbook – Getting started with Common Lisp](https://lispcookbook.github.io/cl-cookbook/getting-started.html)