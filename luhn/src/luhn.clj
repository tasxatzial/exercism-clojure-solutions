(ns luhn)

(defn remove-spaces
  "Removes spaces from the given string."
  [s]
  (remove #(= \space %) s))

(defn string->digits
  "Converts a string that represents a non-negative integer
  to a sequence of its digits (integers)."
  [s]
  (map #(- (int %) 48) s))

(defn valid-int?
  "Returns true if the given collection consists of numbers from 0 to 9,
  else false. Collections with less than 2 items are considered invalid."
  [digits]
  (and (> (count digits) 1)
       (every? #(<= 0 % 9) digits)))

(defn luhn-value
  "Returns the value of a number according to the luhn
  formula. The number is represented by a collection of its digits."
  [digits]
  (let [padded-digits (if (even? (count digits))
                        digits
                        (cons 0 digits))]
    (loop [result 0
           padded-digits padded-digits]
      (if (seq padded-digits)
        (let [[d1 d2 & rest-digits] padded-digits]
          (let [doubled-d1 (* 2 d1)]
            (if (> doubled-d1 9)
              (recur (+ result d2 (- doubled-d1 9)) rest-digits)
              (recur (+ result d2 doubled-d1) rest-digits))))
        result))))
      
(defn valid?
  "Returns true if the given string represents a valid
  luhn number, false otherwise."
  [s]
  (let [digits (string->digits (remove-spaces s))]
    (and (valid-int? digits)
         (int? (/ (luhn-value digits) 10)))))
