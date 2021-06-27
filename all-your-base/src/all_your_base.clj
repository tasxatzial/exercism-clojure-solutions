(ns all-your-base)

(defn valid-num?
  "Returns true if the digits list represents a valid
  number in the given base, false otherwise."
  [digits base]
  (every? #(and (>= % 0) (< % base))
          digits))

(defn other->base10
  "Converts a number represented by the list of its digits
  in the given base to its decimal value."
  [digits in-base]
  (when (and (seq digits)
             (> in-base 1)
             (valid-num? digits in-base))
    (+ (reduce (fn [result digit]
                 (* in-base (+ result digit)))
               0
               (butlast digits))
       (last digits))))

(defn base10->other
  "Converts a decimal number to the list of its digits
  in the given base."
  [n out-base]
  (when (and (not= nil n) (> out-base 1))
    (loop [result '()
           num n]
      (if (= num 0)
        (or (seq result) '(0))
        (recur (cons (rem num out-base) result)
               (quot num out-base))))))

(defn convert
  "Converts a number represented by the list of its digits
  in the given input-base to the list of its digits in the
  given output-base. Returns nil if the bases or the number
  are invalid."
  [input-base digits output-base]
  (let [decimal (other->base10 digits input-base)]
    (base10->other decimal output-base)))
