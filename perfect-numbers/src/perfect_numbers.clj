(ns perfect-numbers)

(defn get-prime-factors
  "Returns the prime factors of n. Some of them may be repeated."
  [n]
  (loop [result []
         candidate 2
         n n]
    (if (= n 1)
      result
      (if (zero? (mod n candidate))
        (recur (conj result candidate) candidate (/ n candidate))
        (recur result (inc candidate) n)))))

(defn get-distinct-factors
  "Returns the distinct factors of n."
  [n]
  (loop [result [1]
         num n
         [factor & rest-factors] (get-prime-factors n)]
    (if (seq rest-factors)
      (let [factor1 (/ num factor)
            factor2 (/ n factor1)]
        (recur (conj result factor1 factor2) factor1 rest-factors))
      result)))

(defn classify
  "Determines if a number is perfect, abundant, or deficient."
  [n]
  (if (pos? n)
    (let [factors-sum (reduce + (get-distinct-factors n))]
      (cond
        (> factors-sum n) :abundant
        (= factors-sum n) :perfect
        (< factors-sum n) :deficient))
    (throw (IllegalArgumentException.))))
