(define (factorial x)
  (if (= x 0)
    1
    (* x (factorial (- x 1)))))
(display "The factorial of 5 is ")
(display(factorial 5))
