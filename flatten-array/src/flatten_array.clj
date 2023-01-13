(ns flatten-array)

(defn flatten
  [xs]
  (reduce (fn flat- [result x]
            (if (sequential? x)
              (reduce flat- result x)
              (if (nil? x)
                result
                (conj result x))))
          []
          xs))
