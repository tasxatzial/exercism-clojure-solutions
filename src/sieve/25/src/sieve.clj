(ns sieve)

;; solution 2
;; improved version of solution 1 + transients

(defn mark-multiples
  "Sets to nil all candidates x which are multiples of i and
  satisfy i*i <= x <= num."
  [i candidates num]
  (let [max-k (int (/ num i))
        end (if (even? max-k) (dec max-k) max-k)
        kk (range end (dec i) -2)]
    (reduce (fn [result k]
              (if (get result (/ (- k 3) 2))
                (assoc! result (/ (- (* i k) 3) 2) nil)
                result))
            candidates
            kk)))

(defn mark-composites
  "Sets to nil all composites numbers in [2, num]."
  [num]
  (let [candidates (transient (vec (range 3 (inc num) 2)))
        ii (range 3 (inc (int (clojure.math/sqrt num))) 2)]
    (cons 2 (persistent!
              (reduce (fn [result i]
                        (if (get result (/ (- i 3) 2))
                          (mark-multiples i result num)
                          result))
                      candidates
                      ii)))))

(defn sieve
  [num]
  (if (< num 2)
    ()
    (filter some? (mark-composites num))))