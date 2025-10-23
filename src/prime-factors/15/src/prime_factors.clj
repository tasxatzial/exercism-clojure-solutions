(ns prime-factors
  (:require [clojure.math :as math]))

(defn of
  "Returns the prime factors of the given number."
  [num]
  (loop [result []
         candidate 2
         n num]
    (if (= n 1)
      result
      (if (zero? (mod n candidate))
        (recur (conj result candidate) candidate (quot n candidate))
        (recur result (inc candidate) n)))))