(ns luhn)

(defn string->digits
  "Converts a string that represents a non-negative integer
  to a sequence of its digits (integers)."
  [s]
  (map #(Character/digit ^char % 10) s))

(defn valid-number?
  "Returns true if digits consists of numbers from 0 to 9 and has
  more than 1 elements, false otherwise."
  [digits]
  (and (> (count digits) 1)
       (every? #(<= 0 % 9) digits)))

(defn pad
  [digits]
  (if (even? (count digits))
    digits
    (conj digits 0)))

(defn transform
  "Transforms the value at the given index according to the luhn
  formula:
  1) Values at odd indices remain the same.
  2) Values at even indices are doubled and then reduced by 9 if
  result is > 9."
  [idx val]
  (if (odd? idx)
    val
    (let [dval (* 2 val)]
      (if (> dval 9)
        (- dval 9)
        dval))))

(defn luhn-value
  "Returns the value of the given sequence of digits according to the
  luhn formula."
  [digits]
  (let [padded-digits (pad digits)]
    (->> padded-digits
         (map-indexed transform)
         (reduce +))))

(defn valid?
  "Returns true if the given string represents a valid
  luhn number, false otherwise."
  [s]
  (let [digits (string->digits (remove #{\space} s))]
    (and (valid-number? digits)
         (int? (/ (luhn-value digits) 10)))))
