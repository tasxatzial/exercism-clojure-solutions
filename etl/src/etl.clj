(ns etl)

(defn transform
  [source]
  (reduce (fn [res-final [score words]]
            (reduce (fn [res word]
                      (assoc res (clojure.string/lower-case word) score))
                    res-final
                    words))
          {}
          source))
