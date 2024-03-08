(define (fibonacci n)
  (fibonacci-iter n 0 1))

(define (fibonacci-iter n a b)
  (do ((i n (- i 1))
        (x a b)
        (y b (+ a b)))
    ((= i 0) x)
    (set! a x)
    (set! b y)))

(display (fibonacci 100))
