(ns flatten-array)

;; non-lazy

(defn flatten
  "Flattens the given sequential collection.
  Nil values are excluded from the result."
  [coll]
  (reduce (fn flat- [result x]
            (if (sequential? x)
              (reduce flat- result x)
              (if (nil? x)
                result
                (conj result x))))
          []
          coll))