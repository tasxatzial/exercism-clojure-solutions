(ns flatten-array)

;; solution 1: non lazy

(defn flatten
  [xs]
  (reduce (fn _flat [result x]
            (if (sequential? x)
              (reduce _flat result x)
              (if (nil? x)
                result
                (conj result x))))
          []
          xs))
