(ns grains)

(defn mult-by-self
  "Returns the square of the given number."
  [n]
  (*' n n))

(defn mult-by-2
  "Multiplies the given number by 2."
  [n]
  (*' n 2))

;; Time complexity: O(log2_N)
(defn pow2n
  "Returns 2^n where n is integer >= 0."
  [n]
  (if (zero? n)
    1
    (loop [result []
           n n]
      (cond
        (= 1 n) (reduce #(%2 %1) 2 (rseq result))
        (even? n) (recur (conj result mult-by-self) (/ n 2))
        :else (recur (conj result mult-by-2) (dec n))))))

(defn square
  "Returns the number of grains in square n (1 <= n <= 64)."
  [n]
  (pow2n (dec n)))

(defn total
  "Returns the total number of grains."
  []
  (dec (square 65)))
