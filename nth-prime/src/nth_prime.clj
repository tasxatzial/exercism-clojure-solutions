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
;; solution2: using a sieve

(defn composites
  "Returns a lazy seq of composites in [2, max].
  Even numbers are left out."
  [max]
  (for [i (range 3 (inc (Math/round (Math/sqrt max))) 2)
        k (range i (inc (/ max i)) 2)]
    (* i k)))

(defn sieve
  "Returns all primes in [2, max]."
  [max]
  (if (< max 2)
    ()
    (let [numbers (cons 2 (range 3 (inc max) 2))]
      (remove (set (composites max)) numbers))))

(defn sieve-range
  "Returns a range that contains the Nth prime. This
  is valid for n>= 6."
  [N]
  (let [b (+ (* (Math/log N) N)
             (* N (Math/log (Math/log N))))]
    [(- b N) b]))

(defn nth-prime
  "Returns the nth prime."
  [n]
  (cond
    (zero? n) (throw (IllegalArgumentException.))
    (< n 6) (nth [2 3 5 7 11] (dec n))
    :else (let [upper-lim (second (sieve-range n))
                primes (sieve upper-lim)]
            (nth primes (dec n)))))
