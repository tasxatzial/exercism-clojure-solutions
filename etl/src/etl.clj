(ns etl)

(defn transform
  [source]
  (reduce (fn [result [score words]]
            (->> words
                 (map clojure.string/lower-case)
                 (mapv #(vector % score))
                 (into result)))
          {}
          source))
