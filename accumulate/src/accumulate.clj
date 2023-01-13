(ns accumulate)

(defn accumulate
  [f coll]
  (loop [result []
         coll coll]
    (if (seq coll)
      (let [fx (f (first coll))]
        (recur (conj result fx) (rest coll)))
      result)))

;; lazy
(defn accumulate-lazy
  [f coll]
  (lazy-seq
    (when (seq coll)
      (cons (f (first coll)) (accumulate-lazy f (rest coll))))))
