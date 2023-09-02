(ns trinary)

(defn trinary-char->dec
  "Returns the decimal value of a lowercase trinary char."
  [c]
  (- (int c) 48))

(defn trinary->dec-values
  "Converts a string that represents a non-negative trinary
  number to a sequence of the decimal values of its digits."
  [s]
  (map trinary-char->dec s))

(defn valid-trinary?
  "Returns true if the given decimal values represent a valid
  trinary number, false otherwise."
  [decimal-values]
  (every? #(<= 0 % 2) decimal-values))

(defn compute-decimal
  "Converts the given decimal values to the corresponding base 10 number
  assuming they represent a trinary number."
  [decimal-values]
  (when (valid-trinary? decimal-values)
    (reduce #(+ %2 (* 3 %1))
            0 decimal-values)))

(defn to-decimal
  "Converts the given trinary number (string) to the decimal equivalent.
  Returns 0 if the string does not represent a valid trinary number."
  [s]
  (let [dec-values (trinary->dec-values s)]
    (or (compute-decimal dec-values) 0)))
