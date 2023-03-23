(ns nth-prime)

(defn prime?
  "Returns true if N is prime. This will happen if N is not a multiple
  of any number in the given list of primes."
  [N primes]
  (reduce (fn [result prime]
            (if (> prime (inc (Math/sqrt N)))
              (reduced true)
              (if (= 0 (mod N prime))
                (reduced false)
                result)))
          true
          primes))

(defn primes
  "Returns all primes in [2, n]."
  [n]
  (loop [n n
         result []
         i 2]
    (if (zero? n)
      result
      (if (prime? i result)
        (recur (dec n) (conj result i) (inc i))
        (recur n result (inc i))))))

(defn nth-prime
  [n]
  (if (zero? n)
    (throw (IllegalArgumentException.))
    (peek (primes n))))
