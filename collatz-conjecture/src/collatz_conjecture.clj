(ns collatz-conjecture)

(defn next-num
  "Calculates the next number in the collatz conjecture
  algorithm."
  [n]
  (if (even? n)
    (/ n 2)
    ((comp inc *) n 3)))

(defn collatz
  "Applies the collatz conjecture algorithm and returns
  the number of iterations until the specified number
  equals to 1."
  [n]
  (if (pos-int? n)
    (loop [num n
           steps 0]
      (if (= num 1)
        steps
        (recur (next-num num) (inc steps))))
    (throw (Exception.))))

;; solution2: using iterate
(defn collatz2
  [n]
  (if (pos-int? n)
    (count (take-while #(not= 1 %) (iterate next-num n)))
    (throw (Exception.))))
