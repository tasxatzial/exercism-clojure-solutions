(ns nth-prime)

(defn next-prime
  "Returns the next prime given a vector of primes
  in increasing order"
  [primes]
  (loop [candidate (inc (peek primes))
         sqr-primes (take-while #(< % (inc (Math/sqrt candidate)))
                                 primes)]
    (if (some #(zero? (mod candidate %))
              sqr-primes)
      (recur (inc candidate) sqr-primes)
      candidate)))

(defn nth-prime
  "Returns the nth prime."
  [n]
  (cond
    (zero? n) (throw (IllegalArgumentException.))
    (= 1 n) 2
    :else (loop [primes [2]]
            (if (= n (count primes))
              (peek primes)
              (recur (conj primes (next-prime primes)))))))
