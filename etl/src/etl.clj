(ns etl)

(defn add-same-scored-letters
  [result [score letters]]
  (reduce (fn [result letter]
            (assoc result (clojure.string/lower-case letter) score))
          result
          letters))

(defn transform
  [source]
  (reduce add-same-scored-letters {} source))
