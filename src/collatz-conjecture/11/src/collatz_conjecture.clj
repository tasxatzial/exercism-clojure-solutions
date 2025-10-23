(ns collatz-conjecture)

(defn collatz-next
  [num]
  (if (even? num)
    (/ num 2)
    (inc (* num 3))))

(defn collatz
  "Returns the number of steps for num to reach 1
  according to the Collatz Conjecture."
  [num]
  (loop [n num
         steps 0]
    (if (= n 1)
      steps
      (recur (collatz-next n) (inc steps)))))
