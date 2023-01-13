(ns word-count)

(defn word-count
  [phrase]
  (->> phrase
       clojure.string/lower-case
       (re-seq #"[A-Za-z]+'*['A-Za-z]*|[0-9]+")
       frequencies))
