(ns binary)

(defn char->int
  "Converts a numeric char to the corresponding integer
  (e.g. \1 is converted to the integer 1)."
  [c]
  (- (int c) 48))

(defn valid-binary?
  "Returns true if digits represents a valid binary number,
  false otherwise."
  [digits]
  (every? #{0 1} digits))

(defn get-decimal
  "Returns the decimal value of a binary number."
  [digits]
  (+ (last digits)
     (reduce (fn [result digit]
               (* 2 (+ result digit)))
             0
             (butlast digits))))

(defn to-decimal
  [s]
  (let [digits (map char->int s)]
    (if (valid-binary? digits)
      (get-decimal digits)
      0)))
