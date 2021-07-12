(ns nth-prime)

(defn composite?
  "Returns true if any of the primes is a factor of n,
  false otherwise."
  [n primes]
  (let [sqr-primes (take-while #(< % (inc (Math/sqrt n)))
                               primes)]
    (some #(zero? (mod n %))
          sqr-primes)))

(defn next-prime
  "Returns the next prime given a vector of primes
  in increasing order."
  [primes]
  (loop [candidate (inc (peek primes))]
    (if (composite? candidate primes)
      (recur (inc candidate))
      candidate)))

(defn nth-prime2
  "Returns the nth prime."
  [n]
  (cond
    (zero? n) (throw (IllegalArgumentException.))
    (= 1 n) 2
    :else (loop [primes [2]]
            (if (= n (count primes))
              (peek primes)
              (recur (conj primes (next-prime primes)))))))

;; -------------------------------
;; solution2: using a sieve
;; this is a lot faster for large N

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
