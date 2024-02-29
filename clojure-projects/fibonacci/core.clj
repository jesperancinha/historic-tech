(ns myproject.core)

(defn factorial [n]
  (loop [n n
         acc 1]
    (if (zero? n)
      acc
      (recur (dec n) (* acc n)))))
