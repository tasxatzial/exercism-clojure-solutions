(ns etl)

(defn transform
  "Transform the legacy data to the shiny new format."
  [source]
  (reduce (fn [result [score words]]
            (let [lower-words (map clojure.string/lower-case words)]
              (into result (mapv #(vector % score) lower-words))))
          {} source))
