(ns collatz-conjecture)

(defn collatz-next
  "Calculates the next number in the collatz conjecture algorithm."
  [n]
  (if (even? n)
    (/ n 2)
    ((comp inc *) n 3)))

(defn collatz
  "Returns the number of steps required to reach 1."
  [n]
  (if (pos-int? n)
    (loop [n n
           steps 0]
      (if (= n 1)
        steps
        (recur (collatz-next n) (inc steps))))
    (throw (Exception.))))
