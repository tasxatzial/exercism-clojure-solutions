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

;; lazy
(defn flatten-lazy
  [xs]
  (lazy-seq
    (when (seq xs)
      (let [[x & rest-xs] xs]
        (if (sequential? x)
          (concat (flatten-lazy x) (flatten-lazy rest-xs))
          (if (nil? x)
            (flatten-lazy rest-xs)
            (cons x (flatten-lazy rest-xs))))))))
