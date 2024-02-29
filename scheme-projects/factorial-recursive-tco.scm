(define (factorial n)
  (define (factorial-iter n accumulator)
    (if (= n 0)
      accumulator
      (factorial-iter (- n 1) (* n accumulator))))
  (factorial-iter n 1))
(display "The factorial of 5 is ")
(display(factorial 5))
