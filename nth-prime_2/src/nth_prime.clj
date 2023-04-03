(ns nth-prime)

;; lazy

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

(defn- add-next-prime
  "Adds the next prime to the given vector of primes."
  [primes]
  (let [n (inc (peek primes))]
    (loop [n n]
      (if (prime? n primes)
        (conj primes n)
        (recur (inc n))))))

(defn gen-primes-seq
  "Returns a lazy sequence of vectors of primes
  [2] [2 3] [2 3 5] [2 3 5 7] ..."
  []
  (iterate add-next-prime [2]))

(def primes-seq (gen-primes-seq))

(defn nth-prime
  [n]
  (if (zero? n)
    (throw (IllegalArgumentException.))
    (peek (nth primes-seq (dec n)))))
