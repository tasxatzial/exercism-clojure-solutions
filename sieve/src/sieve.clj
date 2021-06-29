(ns sieve)

;; optimized sieve

(defn composites
  "Returns a lazy seq of composites in [2, max].
  Even numbers are left out."
  [max]
  (for [i (range 3 (inc (Math/round (Math/sqrt max))) 2)
        k (range i (inc (/ max i)) 2)]
    (* i k)))

(defn sieve
  "Returns all primes in [2, max]. "
  [max]
  (if (< max 2)
    ()
    (remove (set (composites max))
            (cons 2 (range 3 (inc max) 2)))))
