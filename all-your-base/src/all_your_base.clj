(ns all-your-base)

(defn valid-base?
  "Returns true if the given base is an integer > 1, else false."
  [b]
  (and (> b 1) (integer? b)))

(defn valid-num?
  "Multi-arity function;
  1) If called with 1 args: Returns true if the given number is integer >= 0,
  else false.
  2) If called with 2 args: Returns true if the collection of decimal values
  represents a valid number in the given base, else false."
  ([n]
   (and (integer? n) (>= n 0)))
  ([decimal-vals base]
   (and (seq decimal-vals)
        (every? #(and (integer? %) (>= % 0) (< % base))
                decimal-vals))))

(defn to-base10
  "Given a collection of decimal values that represent
  a number in the given base, it computes the number in base 10."
  [decimal-vals base]
  (when (and (valid-num? decimal-vals base) (valid-base? base))
    (reduce #(+ %2 (* base %1))
            0 decimal-vals)))

(defn from-base10
  "Converts a base 10 number to a list of its decimal values in the given base."
  [n base]
  (when (and (valid-num? n) (valid-base? base))
    (loop [result ()
           n n]
      (if (zero? n)
        (or (seq result) '(0))
        (let [r (rem n base)
              q (quot n base)]
          (recur (conj result r) q))))))

(defn convert
  [src-base digits target-base]
  (-> digits
      (to-base10 src-base)
      (from-base10 target-base)))
