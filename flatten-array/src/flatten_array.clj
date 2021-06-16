(ns flatten-array)

(defn flatten-no-removal
  "Flattens a sequential struct. Does not remove nil values."
  [coll]
  (reduce (fn [result x]
            (into result
                  (if (sequential? x)
                    (flatten-no-removal x)
                    [x])))
          []
          coll))

;; using flatten-no-removal
(defn flatten
  "Flattens a sequential struct and removes any nil values."
  [coll]
  (->> (flatten-no-removal coll)
       (filter (complement nil?))))

;; using clojure.core/flatten
(defn flatten2
  "Flattens a sequential struct and removes any nil values."
  [coll]
  (->> (clojure.core/flatten coll)
       (filter (complement nil?))))
