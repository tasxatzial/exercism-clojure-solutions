(ns collatz-conjecture)

(defn collatz
  "Applies the collatz conjecture algorithms and returns the number of iterations
  until the specified number equals to 1."
  [n]
  (if (pos-int? n)
    (loop [num n
           steps 0]
      (if (= num 1)
        steps
        (if (= (mod num 2) 0)
          (recur (/ num 2)
                 (inc steps))
          (recur ((comp inc *) num 3)
                 (inc steps)))))
    (throw (Exception.))))
