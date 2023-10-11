(ns accumulate)

(defn accumulate
  [f coll]
  (loop [result []
         coll coll]
    (if (seq coll)
      (let [fx (f (first coll))]
        (recur (conj result fx) (rest coll)))
      result)))
