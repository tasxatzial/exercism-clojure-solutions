(ns sieve)

;; solution 1

(defn get-non-even-composites
  "Returns a lazy seq of composites in [2, n]. Even numbers are excluded."
  [n]
  (for [i (range 3 (inc (Math/round (Math/floor (Math/sqrt n)))) 2)
        k (range i (inc (Math/round (Math/floor (/ n i)))) 2)]
    (* i k)))

(defn sieve
  "Returns all primes in [2, n]. "
  [n]
  (if (< n 2)
    ()
    (let [candidates (cons 2 (range 3 (inc n) 2))
          composites (get-non-even-composites n)]
      (remove (set composites) candidates))))

;; ---------------------------------------------------------
;; solution 2
;; improved version of solution 1 + transients

(defn mark-multiples
  "Sets to nil all candidates x which are multiples of i and
  satisfy i*i <= x <= n."
  [i candidates n]
  (let [max-k (Math/round (Math/floor (/ n i)))
        end (if (even? max-k) (dec max-k) max-k)
        kk (range end (dec i) -2)]
    (reduce (fn [result k]
              (if (get result (/ (- k 3) 2))
                (assoc! result (/ (- (* i k) 3) 2) nil)
                result))
            candidates
            kk)))

(defn mark-composites
  "Sets to nil all composites numbers in [2, n]."
  [n]
  (let [candidates (transient (vec (range 3 (inc n) 2)))
        ii (range 3 (Math/round (Math/ceil (Math/sqrt n))) 2)]
    (cons 2 (persistent!
              (reduce (fn [result i]
                        (if (get result (/ (- i 3) 2))
                          (mark-multiples i result n)
                          result))
                      candidates
                      ii)))))

(defn sieve2
  [n]
  (filter some? (mark-composites n)))
