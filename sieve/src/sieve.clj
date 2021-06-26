(ns sieve)

(defn sieve
  "Returns all the primes from 2 up to a given number N."
  [N]
  (let [numbers (range 2 (inc N))]
    (loop [result []
           [num & more] numbers]
      (if num
        (recur (conj result num)
               (filter #(not= 0 (mod % num))
                       more))
        result))))
