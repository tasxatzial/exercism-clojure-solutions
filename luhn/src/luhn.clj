(ns luhn)

(defn string->digits
  "Converts a string that represents a non-negative integer
  to a sequence of its digits (integers)."
  [s]
  (map #(Character/digit ^char % 10) s))

(defn valid-int?
  "Returns true if the given collection consists of numbers from 0 to 9,
  else false. Collections with less than 2 items are considered invalid."
  [digits]
  (and (> (count digits) 1)
       (every? #(<= 0 % 9) digits)))

(defn pad
  [digits]
  (if (even? (count digits))
    digits
    (conj digits 0)))

(defn luhn-value
  "Returns the value of a number according to the luhn
  formula. The number is represented by a collection of its digits."
  [digits]
  (loop [result 0
         padded-digits (pad digits)]
    (if (seq padded-digits)
      (let [[d1 d2 & rest-digits] padded-digits]
        (let [doubled-d1 (* 2 d1)]
          (if (> doubled-d1 9)
            (recur (+ result d2 (- doubled-d1 9)) rest-digits)
            (recur (+ result d2 doubled-d1) rest-digits))))
      result)))

(defn valid?
  "Returns true if the given string represents a valid
  luhn number, false otherwise."
  [s]
  (let [digits (string->digits (remove #{\space} s))]
    (and (valid-int? digits)
         (int? (/ (luhn-value digits) 10)))))
