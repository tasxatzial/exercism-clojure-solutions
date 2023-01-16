(ns binary)

(defn bin-char->dec
  "Returns the decimal value of a lowercase binary char."
  [c]
  (- (int c) 48))

(defn bin->dec-values
  "Converts a string that represents a non-negative binary
  number to a seq of the decimal values of its digits."
  [s]
  (map bin-char->dec s))

(defn valid-bin?
  "Returns true if the seq of decimal values represents a valid
  binary number, false otherwise."
  [decimal-values]
  (every? #(<= 0 % 1) decimal-values))

(defn compute-decimal
  "Given a seq of decimal values that represent a binary
  number (0 <= val < 2), it computes the number in base 10."
  [decimal-values]
  (+ (last decimal-values)
     (reduce (fn [result digit]
               (* 2 (+ result digit)))
             0
             (butlast decimal-values))))

(defn to-decimal
  "Converts the given binary number (string) to the decimal equivalent.
  Returns 0 if the string does not represent a valid binary number."
  [s]
  (let [dec-values (bin->dec-values s)]
    (if (valid-bin? dec-values)
      (compute-decimal dec-values)
      0)))
