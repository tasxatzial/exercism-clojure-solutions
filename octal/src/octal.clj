(ns octal)

(defn string->digits
  "Converts a string to a seq of its digits. Numeric characters
  are guaranteed to be represented by their numerical value."
  [s]
  (map #(- (int %) 48) s))

(defn valid-octal?
  "Returns true iff digits seq represents a valid octal number."
  [digits]
  (every? #(<= 0 % 7) digits))

(defn compute-val
  "Returns the decimal value of a octal number. The number is
  represented by a seq of its decimal digit values."
  [digits]
  (+ (reduce (fn [result digit]
               (* 8 (+ result digit)))
             0
             (butlast digits))
     (last digits)))

(defn to-decimal
  "Returns the decimal value of the octal number represented
  by the given string or 0 if the string does not represent a valid
  octal number."
  [s]
  (let [digits (string->digits s)]
    (if (valid-octal? digits)
      (compute-val digits)
      0)))
