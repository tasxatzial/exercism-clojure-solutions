(ns flatten-array)

;;using reduce
(defn flatten
  "Flattens a sequence and removes any nil values"
  [arr]
  (let [flattened (reduce (fn [result x]
                            (if (sequential? x)
                              (concat result (flatten x))
                              (concat result [x])))
                          '() arr)]
    (filter (complement nil?) flattened))
)

;;using clojure.core/flatten
(defn flatten2
  "Flattens a sequence and removes any nil values"
  [arr]
  (filter (complement nil?) (clojure.core/flatten arr))
)
