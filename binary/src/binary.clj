(ns binary)

(defn bin-char->dec
  "Returns the decimal value of a lowercase binary char."
  [c]
  (- (int c) 48))

(defn bin->dec-values
  "Converts a string that represents a non-negative binary
  number to a sequence of the decimal values of its digits."
  [s]
  (map bin-char->dec s))

(defn valid-bin?
  "Returns true if the given decimal values represent a valid
  binary number, false otherwise."
  [decimal-values]
  (every? #(<= 0 % 1) decimal-values))

(defn compute-decimal
  "Converts the given decimal values to the corresponding base 10 number
  assuming they represent a binary number."
  [decimal-values]
  (when (valid-bin? decimal-values)
    (reduce #(+ %2 (* 2 %1))
            0 decimal-values)))

(defn to-decimal
  "Converts the given binary number (string) to the decimal equivalent.
  Returns 0 if the string does not represent a valid binary number."
  [s]
  (let [dec-values (bin->dec-values s)]
    (or (compute-decimal dec-values) 0)))
