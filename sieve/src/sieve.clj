(ns sieve)

(defn multiples-of
  "Generates a set of all multiples of n that are <= max
  and >= n^2."
  [n max]
  (set (take-while #(<= % max)
                   (iterate #(+ % n) (* n n)))))

(defn sieve
  "Returns all primes in [2, max]."
  [max]
  (loop [primes []
         [n & remaining] (range 2 (inc max))]
    (if n
      (recur (conj primes n)
             (remove (multiples-of n max) remaining))
      primes)))
