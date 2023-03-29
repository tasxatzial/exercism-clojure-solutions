(ns etl)

(defn transform
  [source]
  (reduce (fn [result [score words]]
            (->> words
                 (map #(vector (clojure.string/lower-case %) score))
                 (into result)))
          {}
          source))
