(ns armstrong-numbers)

(defn int->digits
  "Converts a non-negative integer to a seq of its digits."
  [x]
  (->> x
       str
       (map #(Character/digit ^char % 10))))

;; Time complexity: O(log2_N)
(defn math-pow
  "Returns x^n where n is integer > 0."
  [x n]
  (let [times-x (partial *' x)
        square #(*' % %)]
    (loop [result []
           n n]
      (cond
        (= 1 n) (reduce #(%2 %1) x (rseq result))
        (even? n) (recur (conj result square) (/ n 2))
        :else (recur (conj result times-x) (dec n))))))

(defn armstrong?
  "Returns true if the given number is an Armstrong number;
  otherwise, it returns false."
  [num]
  (let [digits (int->digits num)
        digit-count (count digits)]
    (->> digits
         (map #(math-pow % digit-count))
         (reduce +)
         (= num))))
