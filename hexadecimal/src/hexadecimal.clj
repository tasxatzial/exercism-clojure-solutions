(ns hexadecimal)

(defn hex-char->dec
  "Returns the decimal value of a lowercase hexadecimal char."
  [c]
  (let [val (int c)]
    (if (<= 48 val 57)
      (- val 48)
      (+ 10 (- val 97)))))

(defn hex->dec-values
  "Converts a string that represents a non-negative hexadecimal
  number to a seq of the decimal values of its digits."
  [s]
  (map hex-char->dec s))

(defn valid-hex?
  "Returns true if the seq of decimal values represents a valid
  hexadecimal number, false otherwise."
  [decimal-values]
  (every? #(<= 0 % 15) decimal-values))

(defn compute-decimal
  "Given a seq of decimal values that represent a hexadecimal
  number, it computes the decimal equivalent of that number."
  [decimal-values]
  (+ (last decimal-values)
     (reduce (fn [result digit]
               (* 16 (+ result digit)))
             0
             (butlast decimal-values))))

(defn hex-to-int
  "Converts the given hex number (string) to the decimal equivalent.
  Returns 0 if the string does not represent a valid hex number."
  [s]
  (let [dec-values (hex->dec-values s)]
    (if (valid-hex? dec-values)
      (compute-decimal dec-values)
      0)))
