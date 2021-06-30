(ns trinary)

(defn string->digits
  "Converts a string to a seq of its digits. Numeric characters
  are guaranteed to be represented by their numerical value."
  [s]
  (map #(- (int %) 48)
       s))

(defn valid-trinary?
  "Returns true iff digits seq represents a valid trinary number."
  [digits]
  (every? #(<= 0 % 2) digits))

(defn compute-val
  "Returns the decimal value of a trinary number represented
  by a seq of its digits."
  [digits]
  (+ (reduce (fn [result digit]
               (* 3 (+ result digit)))
             0 (butlast digits))
     (last digits)))

(defn to-decimal
  "Returns the decimal value of the trinary number represented
  by the given string or 0 if the string does not represent a valid
  trinary number."
  [s]
  (let [digits (string->digits s)]
    (if (valid-trinary? digits)
      (compute-val digits)
      0)))
