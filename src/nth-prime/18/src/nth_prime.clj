(ns nth-prime)

;; non-lazy solution

(defn- prime?
  "Returns true if N is prime. This will happen if N is a not multiple
  of any number in the given vector of primes."
  [N primes]
  (let [sqrt-N (Math/sqrt N)]
    (reduce (fn [result prime]
            (if (> prime sqrt-N)
              (reduced true)
              (if (= 0 (mod N prime))
                (reduced false)
                result)))
          true
          primes)))

(defn- primes
  "Returns the first n primes."
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
  "Returns the nth prime number."
  [n]
  (if (zero? n)
    (throw (IllegalArgumentException.))
    (peek (primes n))))