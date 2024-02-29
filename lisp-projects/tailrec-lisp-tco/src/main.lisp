(defpackage tailrec-lisp-tco
  (:use :cl))
(in-package :tailrec-lisp-tco)

;; blah blah blah.

(define (factorial n)
  (define (factorial-iter n accumulator)
    (if (= n 0)
        accumulator
        (factorial-iter (- n 1) (* n accumulator))))
  (factorial-iter n 1))
