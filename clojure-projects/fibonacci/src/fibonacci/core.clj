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

(defn calculate-factorial [n]
  (let [result (factorial n)]
    (println "The result is:" result)))