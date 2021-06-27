(ns armstrong-numbers)

(defn int->digits
  "Converts an integer to a seq of its digits."
  [s]
  (map #(Character/digit ^char % 10) (str s)))

(defn math-pow
  "Calculates x^n."
  [x n]
  (loop [result 1
         remaining n]
    (if (= 0 remaining)
      result
      (recur (* x result) (dec remaining)))))

(defn digits-sum
  "Returns the sum of the N-th powers of the digits seq where
  N is the size of the digits seq."
  [digits]
  (reduce #(+ (math-pow %2 (count digits)) %1)
          0
          digits))

(defn armstrong?
  "Determines whether a number is an Armstrong number."
  [n]
  (let [digits (int->digits n)
        sum (digits-sum digits)]
    (= n sum)))
