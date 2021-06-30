(ns hexadecimal)

(defn hex-value
  "Returns the decimal value of a lowercase hexadecimal char."
  [c]
  (let [val (int c)]
    (if (<= 48 val 57)
      (- val 48)
      (+ 10 (- val 97)))))

(defn string->digit-values
  "Converts a string representing a hexadecimal number to a seq of
  the decimal values of its chars."
  [s]
  (map hex-value s))

(defn valid-hexadecimal?
  "Returns true iff digits seq represents a valid hexadecimal number."
  [digits]
  (every? #(<= 0 % 15) digits))

(defn compute-val
  "Returns the decimal value of a hexadecimal number. The number is
  represented by the seq of its decimal digit values."
  [digits]
  (+ (reduce (fn [result digit]
               (* 16 (+ result digit)))
             0 (butlast digits))
     (last digits)))

(defn hex-to-int
  "Returns the decimal value of the hexadecimal number represented
  by the given string or 0 if the string does not represent a valid
  hexadecimal number."
  [s]
  (let [digits (string->digit-values s)]
    (if (valid-hexadecimal? digits)
      (compute-val digits)
      0)))
