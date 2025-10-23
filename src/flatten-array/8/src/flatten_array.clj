(ns flatten-array)

;; lazy

(defn flatten
  "Flattens the given sequential collection.
  Nil values are excluded from the result."
  [coll]
  (lazy-seq
    (when (seq coll)
      (let [[x & rest-coll] coll]
        (if (sequential? x)
          (concat (flatten x) (flatten rest-coll))
          (if (nil? x)
            (flatten rest-coll)
            (cons x (flatten rest-coll))))))))