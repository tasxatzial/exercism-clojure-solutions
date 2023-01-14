(ns trinary)

(defn trinary-char->dec
  "Returns the decimal value of a lowercase trinary char."
  [c]
  (- (int c) 48))

(defn trinary->dec-values
  "Converts a string that represents a non-negative trinary
  number to a seq of the decimal values of its digits."
  [s]
  (map trinary-char->dec s))

(defn valid-trinary?
  "Returns true if the seq of decimal values represents a valid
  trinary number, false otherwise."
  [decimal-values]
  (every? #(<= 0 % 2) decimal-values))

(defn compute-decimal
  "Given a seq of decimal values that represent a trinary
  number, it computes the decimal equivalent of that number."
  [decimal-values]
  (+ (last decimal-values)
     (reduce (fn [result digit]
               (* 3 (+ result digit)))
             0
             (butlast decimal-values))))

(defn to-decimal
  "Converts the given trinary number (string) to the decimal equivalent.
  Returns 0 if the string does not represent a valid trinary number."
  [s]
  (let [dec-values (trinary->dec-values s)]
    (if (valid-trinary? dec-values)
      (compute-decimal dec-values)
      0)))
