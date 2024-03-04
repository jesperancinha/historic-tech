# ASNSEI-THE-RIGHT-WAF - Lisp projects

## Structure

-   [tail recursion](./tail-recursion) - A tail recursion project

## Install LISP

```shell
apt-get install sbcl
apt-get install rlwrap
apt-get install cl-quicklisp
```

Or you can just run `Make install-all`

## Quick Lisp Loading and installation

```common lisp
(load "/usr/share/common-lisp/source/quicklisp/quicklisp.lisp")
(quicklisp-quickstart:install)
(ql:quickload '("str" "cl-ppcre" "alexandria", "cl-project", "rove"))
(ql:quickload "str")
(ql:quickload "cl-project")
(ql:quickload "rove")
```

## Easy install

Check the Makefile for more details, but if you need to install the three packages on Linux, make sure to use the following makefile command:

```shell
make install-all
```

