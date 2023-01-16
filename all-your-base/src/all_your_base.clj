(ns all-your-base)

(defn valid-num?
  "Returns true if the decimal values represent a valid
  number in the given base, false otherwise."
  [decimal-vals base]
  (every? #(and (>= % 0) (< % base))
          decimal-vals))

(defn to-base10
  "Given a seq of decimal values that represent a number in the
  given base (0 <= val < base), it computes the number in base 10."
  [decimal-vals base]
  (when (and (seq decimal-vals) (> base 1) (valid-num? decimal-vals base))
    (+ (reduce #(* base (+ %1 %2))
               0
               (butlast decimal-vals))
       (last decimal-vals))))

(defn from-base10
  "Converts a non-negative base 10 number to a seq of its decimal
  values in the given base."
  [n base]
  (when (and (not (nil? n)) (> base 1))
    (loop [result '()
           n n]
      (if (zero? n)
        (or (seq result) '(0))
        (let [remainder (rem n base)
              quotient (quot n base)]
          (recur (cons remainder result) quotient))))))

(defn convert
  [src-base digits target-base]
  (-> (to-base10 digits src-base)
      (from-base10 target-base)))
