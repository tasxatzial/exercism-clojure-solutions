(ns collatz-conjecture)

(defn collatz-next
  "Calculates the next number in the collatz conjecture algorithm."
  [n]
  (if (even? n)
    (/ n 2)
    ((comp inc *) n 3)))

(defn collatz
  [n]
  (if (pos-int? n)
    (loop [n n
           steps 0]
      (if (= n 1)
        steps
        (recur (collatz-next n) (inc steps))))
    (throw (Exception.))))

;; lazy
(defn collatz-seq
  "Returns a lazy seq of the numbers produced by the collatz
  conjecture algorithm for the given n."
  [n]
  (lazy-seq
    (when (not= n 1)
      (cons n (collatz-seq (collatz-next n))))))

(defn collatz2
  [n]
  (if (pos-int? n)
    (count (collatz-seq n))
    (throw (Exception.))))
