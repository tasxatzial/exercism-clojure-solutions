(ns sieve)

;; optimized sieve

(defn- multiples
  "Generates a set of multiples of n that are <=max
  and >=n^2. Also every second multiple is skipped.
  Intended to be used when n>2"
  [n max]
  (set (range (* n n) (inc max) (+ n n))))

(defn sieve
  "Returns all primes in [2, max]."
  [max]
  (if (< max 2)
    ()
    (let [numbers (range 3 (inc max))
          odd (filter odd? numbers)
          remove-multiples #(remove (multiples %2 max) %1)
          primes-gt2 (reduce remove-multiples odd odd)]
      (cons 2 primes-gt2))))
