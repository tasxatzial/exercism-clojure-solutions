(ns binary)

(defn string->digits
  "Converts a string to a seq of its digits. Numeric characters
  are guaranteed to be represented by their numerical value."
  [s]
  (map #(- (int %) 48)
       s))

(defn valid-binary?
  "Returns true iff digits seq represents a valid binary number."
  [digits]
  (every? #(<= 0 % 1) digits))

(defn compute-val
  "Returns the decimal value of a binary number. The number is
  represented by a seq of its decimal digit values."
  [digits]
  (+ (reduce (fn [result digit]
               (* 2 (+ result digit)))
             0
             (butlast digits))
     (last digits)))

(defn to-decimal
  "Returns the decimal value of the binary number represented
  by the given string or 0 if the string does not represent a valid
  binary number."
  [s]
  (let [digits (string->digits s)]
    (if (valid-binary? digits)
      (compute-val digits)
      0)))
