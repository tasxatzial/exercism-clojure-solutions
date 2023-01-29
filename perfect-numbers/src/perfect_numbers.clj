(ns perfect-numbers)

(defn get-non-even-composites
  "Returns a lazy seq of composites in [2, n]. Even numbers are excluded."
  [n]
  (for [i (range 3 (inc (Math/round (Math/sqrt n))) 2)
        k (range i (inc (/ n i)) 2)]
    (* i k)))

(defn sieve
  "Returns all primes in [2, n]. "
  [n]
  (if (< n 2)
    ()
    (let [candidates (cons 2 (range 3 (inc n) 2))
          composites (get-non-even-composites n)]
      (vec (remove (set composites) candidates)))))

(defn composite?
  "Returns true if n has any of the primes as a factor, else false.
  Primes includes all primes which are <= square root of n."
  [n primes]
  (->> primes
       (take-while #(< % (inc (Math/sqrt n))))
       (some #(zero? (mod n %)))
       boolean))

(defn get-factors
  "Returns the prime factors of n. Each factor appears as many times
  as the power of the factor in the prime decomposition of n."
  [n]
  (if (= n 1)
    []
    (let [primes (sieve (Math/round (Math/sqrt n)))]
      (loop [result []
             num n
             i 0]
        (if-let [nth-prime (get primes i)]
          (let [quotient (/ num nth-prime)]
            (if (int? quotient)
              (if (composite? quotient primes)
                (recur (conj result nth-prime) quotient i)
                (if (= 1 quotient)
                  (conj result nth-prime)
                  (conj result nth-prime quotient)))
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
