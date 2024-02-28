(defpackage tailrec-lisp
  (:use :cl)
   (:export
     #:two-power-of)
  )
(in-package :tailrec-lisp)

;; blah blah blah.

(defun two-power-of  (n)
	(cond ((= n 0) 1)
	(t (* 2 (two-power-of (- n 1))))))
