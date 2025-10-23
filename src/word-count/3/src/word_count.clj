(ns word-count)

(defn word-count
  "Counts how many times each word occurs in the given string"
  [s]
  (->> s
       clojure.string/lower-case
       (re-seq #"\b\w+'?\w*\b")
       frequencies))
