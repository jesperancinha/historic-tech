(ns fibonacci.core)

(defn factorial [n]
  (let [n (if (instance? String n)
                (Double/parseDouble n)
                n)]
  (loop [n n
         acc 1.0]
    (if (zero? n)
      acc
      (recur (dec n) (* acc n)))))
  )

(defn fibonacci-iterative [n]
  (let [n (if (instance? String n)
              (Double/parseDouble n)
              n)]
  (if (<= n 1)
    n
    (let [fib-seq (take (+ 1 n) (iterate (fn [[a b]] [b (+ a b)]) [0.0 1.0]))]
      (first (last fib-seq))))))

(defn fibonacci-recursive [n]
  (let [n (if (instance? String n)
            (Double/parseDouble n)
            n)]
  (if (<= n 1.0)
    n
    (+ (fibonacci-recursive (- n 1.0)) (fibonacci-recursive (- n 2.0))))))


(defn fibonacci-tail-rec [n]
  (let [n (if (instance? String n)
              (Double/parseDouble n)
              n)]
  (letfn [(fib-tail [n a b]
            (if (zero? n)
              a
              (fib-tail (dec n) b (+ a b))))]
    (fib-tail n 0.0 1.0))))


(defn fibonacci-tail-rec-tco [n]
  (let [n (if (instance? String n)
            (Double/parseDouble n)
            n)]
  (letfn [(fib [n a b]
            (if (zero? n)
              a
              (recur (dec n) b (+ a b))))]
    (fib n 0.0 1.0))))


(defn calculate-factorial [n]
  (let [result (factorial n)]
    (println "The result is:" result)))

(defn calculate-all-fibonacci [n]
  (let [result (fibonacci-iterative n)]
    (println "The result is:" result))
  (let [result (fibonacci-tail-rec-tco n)]
    (println "The result is:" result)))

(defn calculate-all-slow-fibonacci [n]
  (let [result (fibonacci-tail-rec n)]
    (println "The result is:" result))
  (let [result (fibonacci-recursive n)]
    (println "The result is:" result)))
