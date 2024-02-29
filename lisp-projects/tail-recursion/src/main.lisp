(defpackage tailrec-lisp
  (:use :cl)
   (:export
     #:two-power-of
     #:factorial
     #:factorial-iter)
  )
(in-package :tailrec-lisp)

(defun two-power-of  (n)
	(cond ((= n 0) 1)
	(t (* 2 (two-power-of (- n 1))))))

(defun factorial (n &optional (acc 1))
  (if (zerop n)
      acc
      (factorial (- n 1) (* acc n))))

(defun factorial-iter (n)
  (if (< n 0)
      (error "Factorial is not defined for negative numbers.")
      (loop for i from 1 to n
            for result = 1 then (* result i)
            finally (return result))))

;; How to use
(tailrec-lisp:two-power-of 3)
(format t "Factorial of 5 is ~a" (tailrec-lisp:factorial 5))
(format t "Factorial of 5 is ~a" (tailrec-lisp:factorial-iter 5))
