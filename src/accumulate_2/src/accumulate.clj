(ns accumulate)

;; lazy
(defn accumulate
  [f coll]
  (lazy-seq
    (when (seq coll)
      (cons (f (first coll)) (accumulate f (rest coll))))))
