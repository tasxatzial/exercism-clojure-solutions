(ns luhn)

(defn clean-chars
  "Removes spaces from the given string."
  [s]
  (filter #(not= \space %) s))

(defn- to-digits
  "Converts a seq of chars to a seq of numbers. Numerical
  chars always map to the corresponding numerical value."
  [chars]
  (map #(- (int %) 48)
       chars))

(defn valid-num?
  "Returns true if digits seq represents a valid number.
  Sequences with less than 2 items are considered invalid."
  [digits]
  (and (> (count digits) 1)
       (every? #(<= 0 % 9) digits)))

(defn luhn-value
  "Returns the value of a number according to the luhn
  formula. The number is represented by a seq of its digits."
  [digits]
  (let [partitioned (partition 2 2 [0] (reverse digits))]
    (reduce (fn [result [first-digit second-digit]]
              (let [doubled (* 2 second-digit)]
                (if (> doubled 9)
                  (+ result first-digit (- doubled 9))
                  (+ result first-digit doubled))))
            0 partitioned)))

(defn valid?
  "Returns true if the given string represents a valid
  luhn number, false otherwise."
  [s]
  (let [digits (to-digits (clean-chars s))]
    (and (valid-num? digits)
         (int? (/ (luhn-value digits) 10)))))
