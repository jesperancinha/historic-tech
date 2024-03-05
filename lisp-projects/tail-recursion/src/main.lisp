(defpackage tailrec-lisp
  (:use :cl)
   (:export
     #:two-power-of
     #:factorial
     #:factorial-iter
     #:fibonacci-tail-recursive
     #:fibonacci-iterative
     #:fibonacci-recursive)
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

;;Tail Recursive
(defun fibonacci-tail-recursive (n)
  (if (<= n 1)
      n
      (fib-tail-recursive 0 1 n)))

(defun fib-tail-recursive (a b count)
  (if (= count 0)
      a
      (fib-tail-recursive b (+ a b) (- count 1))))

;Iterative
(defun fibonacci-iterative (n)
  (if (<= n 1)
      n
      (fib-iter 0 1 n)))

(defun fib-iter (a b count)
  (loop repeat (- count 1)
        do (setf (values a b) (values b (+ a b)))
        finally (return b)))

;;Just recursive
(defun fibonacci-recursive (n)
  (if (<= n 1)
      n
      (+ (fibonacci-recursive (- n 1))
         (fibonacci-recursive (- n 2)))))

;; How to use
;(tailrec-lisp:two-power-of 3)
;(format t "Factorial of 5 is ~a" (tailrec-lisp:factorial 5))
;(format t "Factorial of 5 is ~a" (tailrec-lisp:factorial-iter 5))
;(format t "Fibonacci of 100 is ~a" (tailrec-lisp:fibonacci-iterative 100))
;(format t "Fibonacci of 100 is ~a" (tailrec-lisp:fibonacci-tail-recursive 100))
;(format t "Fibonacci of 100 is ~a" (tailrec-lisp:fibonacci-recursive 100))
