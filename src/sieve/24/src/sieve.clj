(ns sieve)

;; solution 1

(defn get-non-even-composites
  "Returns a lazy seq of composites in [2, num]. Even numbers are excluded."
  [num]
  (for [i (range 3 (inc (int (clojure.math/sqrt num))) 2)
        k (range i (inc (int (/ num i))) 2)]
    (* i k)))

(defn sieve
  "Returns the primes that are less than or equal to num."
  [num]
  (if (< num 2)
    ()
    (let [candidates (cons 2 (range 3 (inc num) 2))
          composites (get-non-even-composites num)]
      (remove (set composites) candidates))))