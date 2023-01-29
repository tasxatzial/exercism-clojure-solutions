(ns nth-prime)

;; solution 1

(defn composite?
  "Returns true if n has any of the primes as a factor, else false.
  Primes includes all primes which are <= square root of n."
  [n primes]
  (->> primes
       (take-while #(< % (inc (Math/sqrt n))))
       (some #(zero? (mod n %)))
       boolean))

(defn primes
  "Returns all primes in [2, n]."
  [n]
  (loop [n n
         result []
         i 2]
    (if (zero? n)
      result
      (if (composite? i result)
        (recur n result (inc i))
        (recur (dec n) (conj result i) (inc i))))))

(defn nth-prime
  [n]
  (if (zero? n)
    (throw (IllegalArgumentException.))
    (last (primes n))))

;; ---------------------------------------------
;; solution 2: sieve
;; this is a lot faster for large n

(defn get-non-even-composites
  "Returns a lazy seq of composites in [2, n]. Even numbers are excluded."
  [n]
  (for [i (range 3 (inc (Math/round (Math/floor (Math/sqrt n)))) 2)
        k (range i (inc (Math/round (Math/floor (/ n i)))) 2)]
    (* i k)))

(defn sieve
  "Returns all primes in [2, n]. "
  [n]
  (if (< n 2)
    ()
    (let [candidates (cons 2 (range 3 (inc n) 2))
          composites (get-non-even-composites n)]
      (remove (set composites) candidates))))

(defn sieve-range
  "Returns a range that contains the nth prime. Valid for n>= 6."
  [n]
  (let [b (+ (* (Math/log n) n)
             (* (Math/log (Math/log n)) n))]
    [(- b n) b]))

(defn nth-prime
  [n]
  (cond
    (zero? n) (throw (IllegalArgumentException.))
    (< n 6) (get [2 3 5 7 11] (dec n))
    :else (let [upper-lim (second (sieve-range n))
                primes (sieve upper-lim)]
            (nth primes (dec n)))))
