(ns all-your-base)

(defn valid-base?
  "Returns true if the given base is an integer >1, else false."
  [b]
  (and (> b 1) (integer? b)))

(defn valid-digits?
  "Returns true if the collection of decimal values
  represents a valid number in the given base, else false."
  [decimal-vals base]
  (and (seq decimal-vals)
       (valid-base? base)
       (every? #(and (integer? %) (>= % 0) (< % base))
               decimal-vals)))

(defn valid-num?
  "Returns true if n is an integer >=0, else false."
  [n]
  (and (integer? n) (>= n 0)))

(defn to-base10
  "Converts a collection of decimal values that represent
  a number in the given base to a base 10 number."
  [decimal-vals base]
  (when (valid-digits? decimal-vals base)
    (reduce #(+ %2 (* base %1))
            0 decimal-vals)))

(defn from-base10
  "Converts a base 10 number to a list of its decimal values in the given base."
  [n base]
  (when (and (valid-num? n) (valid-base? base))
    (loop [result ()
           n n]
      (if (zero? n)
        (or (not-empty result) '(0))
        (let [r (rem n base)
              q (quot n base)]
          (recur (conj result r) q))))))

(defn convert
  [src-base digits target-base]
  (-> digits
      (to-base10 src-base)
      (from-base10 target-base)))
