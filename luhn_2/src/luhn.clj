(ns luhn)

(defn remove-spaces
  "Removes spaces from the given string."
  [s]
  (remove #(= \space %) s))

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
  "Returns the value of a number according to the luhn
  formula. The number is represented by a collection of its digits."
  [digits]
  (let [padded-digits (if (even? (count digits))
                        digits
                        (cons 0 digits))]
    (->> padded-digits
         (map-indexed transform)
         (reduce +))))

(defn valid?
  "Returns true if the given string represents a valid
  luhn number, false otherwise."
  [s]
  (let [digits (string->digits (remove-spaces s))]
    (and (valid-int? digits)
         (int? (/ (luhn-value digits) 10)))))
