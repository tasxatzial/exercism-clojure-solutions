(ns perfect-numbers)

(defn prime?
  "Returns true if N is prime. This will happen if N is a multiple
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

(defn get-primes
  "Returns all primes in [2, n]."
  [n]
  (loop [result []
         n0 2]
    (if (> n0 n)
      result
      (if (prime? n0 result)
        (recur (conj result n0) (inc n0))
        (recur result (inc n0))))))

(defn get-factors
  "Returns the prime factors of n. Each factor appears as many times
  as the power of the factor in the prime decomposition of n."
  [n]
  (if (= n 1)
    []
    (let [primes (get-primes (Math/round (Math/sqrt n)))]
      (loop [result []
             num n
             i 0]
        (if-let [nth-prime (get primes i)]
          (let [quotient (/ num nth-prime)]
            (if (int? quotient)
              (if (prime? quotient primes)
                (if (= 1 quotient)
                  (conj result nth-prime)
                  (conj result nth-prime quotient))
                (recur (conj result nth-prime) quotient i))
              (recur result num (inc i))))
          (or (seq result) [n]))))))

(defn get-factors-sum
  "Returns the sum of the factors of n."
  [n]
  (loop [result 1
         num n
         [factor & rest-factors] (get-factors n)]
    (if (seq rest-factors)
      (let [factor1 (/ num factor)
            factor2 (/ n factor1)]
        (recur (+ result factor1 factor2) factor1 rest-factors))
      result)))

(defn classify
  "Determines if a number is perfect, abundant, or deficient."
  [n]
  (if (pos? n)
    (let [factors-sum (get-factors-sum n)]
      (cond
        (> factors-sum n) :abundant
        (= factors-sum n) :perfect
        (< factors-sum n) :deficient))
    (throw (IllegalArgumentException.))))
