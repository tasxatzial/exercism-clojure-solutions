(ns armstrong-numbers)

(defn int->digits
  "Converts an integer to a seq of its digits."
  [s]
  (map #(Character/digit ^char % 10) (str s)))

;; Time complexity O(log2_N)
(defn math-pow
  "Returns x^n."
  [x n]
  (let [multX (partial *' x)
        sqr #(*' % %)]
    (loop [result []
           n n]
      (cond
        (= 1 n) (reduce #(%2 %1) x (rseq result))
        (even? n) (recur (conj result sqr) (/ n 2))
        :else (recur (conj result multX) (dec n))))))

(defn armstrong-sum
  "For the given number n, it returns the sum of its digits each raised to the
  power of the number of digits."
  [n]
  (let [digits (int->digits n)
        digit-count (count digits)]
    (apply +' (map #(math-pow % digit-count) digits))))

(defn armstrong?
  [n]
  (= n (armstrong-sum n)))
