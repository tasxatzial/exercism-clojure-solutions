(ns armstrong-numbers)

(defn int->digits
  "Converts an integer to a seq of its digits."
  [s]
  (map #(Integer/parseInt %)
       (clojure.string/split (str s) #"")))

(defn math-pow
  "Calculates x^n."
  [x n]
  (loop [result 1
         remaining n]
    (if (= 0 remaining)
      result
      (recur (* x result)
             (dec remaining)))))

(defn armstrong?
  "Determines whether a number is an Armstrong number."
  [n]
  (let [digits (int->digits n)
        digit-count (count digits)]
    (loop [sum 0
           [first-digit & remaining-digits] digits]
      (if (boolean first-digit)
        (recur (+ sum (math-pow first-digit digit-count))
               remaining-digits)
        (= sum n)))))
