(ns sieve)

;; optimized sieve

;; solution 1
(defn composites
  "Returns a lazy seq of composites in [2, max].
  Even numbers are left out."
  [max]
  (for [i (range 3 (inc (Math/round (Math/sqrt max))) 2)
        k (range i (inc (/ max i)) 2)]
    (* i k)))

(defn sieve
  "Returns all primes in [2, max]."
  [max]
  (if (< max 2)
    ()
    (remove (set (composites max))
            (cons 2 (range 3 (inc max) 2)))))

;; solution 2
;; Modified algorithm and used transient. This is a lot faster.
(defn delete-mult
  "Deletes multiples of i (except i) from nums
  (replaces them with 0)"
  [i nums max]
  (let [lim (Math/round (Math/floor (/ max i)))
        k (if (even? lim) (dec lim) lim)]
    (loop [k k
           nums nums]
      (if (< k i)
        nums
        (if (pos? (get nums (/ (- k 3) 2)))
          (recur (- k 2) (assoc! nums (/ (- (* i k) 3) 2) 0))
          (recur (- k 2) nums))))))

(defn sieve2
  "Returns all primes in [2, max]."
  [max]
  (let [nums (transient (vec (range 3 (inc max) 2)))
        sqrt (Math/round (Math/floor (Math/sqrt max)))]
    (loop [i 3
           nums nums]
      (if (> i sqrt)
        (cons 2 (filter pos? (persistent! nums)))
        (if (pos? (get nums (/ (- i 3) 2)))
          (recur (+ i 2) (delete-mult i nums max))
          (recur (+ i 2) nums))))))
