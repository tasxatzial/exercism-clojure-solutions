(ns collatz-conjecture)

(defn collatz
  "Return the number of iterations until the specified number equals to 1 according
  to the algorithm described in the Collatz conjecture.

  The specified number must be a positive integer."
  [num]
  (if (or (not (integer? num)) (<= num 0))
    (throw (Exception. "Invalid number."))
    (loop [number num steps 0]
      (if (= number 1)
        steps
        (if (= (mod number 2) 0)
          (recur (/ number 2) (inc steps))
          (recur ((comp inc *) number 3) (inc steps)))))))
