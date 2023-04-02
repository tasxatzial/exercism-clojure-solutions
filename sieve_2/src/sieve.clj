(ns sieve)

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

(defn sieve
  [n]
  (filter some? (mark-composites n)))
