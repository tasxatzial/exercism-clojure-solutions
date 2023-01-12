(ns all-your-base)

(defn valid-num?
  "Returns true if the digits list represents a valid
  number in the given base, false otherwise."
  [digits base]
  (every? #(and (>= % 0) (< % base))
          digits))

(defn to-base10
  "Converts a number represented by a seq of its digits
  in the given base to base 10."
  [digits base]
  (when (and (seq digits) (> base 1) (valid-num? digits base))
    (+ (reduce #(* base (+ %1 %2))
               0
               (butlast digits))
       (last digits))))

(defn from-base10
  "Converts a base 10 number to a seq of its digits
  in the given base."
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
