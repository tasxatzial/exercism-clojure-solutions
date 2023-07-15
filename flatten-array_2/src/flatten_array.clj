(ns flatten-array)

;; solution 2: lazy

(defn flatten
  [xs]
  (lazy-seq
    (when (seq xs)
      (let [[x & rest-xs] xs]
        (if (sequential? x)
          (concat (flatten x) (flatten rest-xs))
          (if (nil? x)
            (flatten rest-xs)
            (cons x (flatten rest-xs))))))))
