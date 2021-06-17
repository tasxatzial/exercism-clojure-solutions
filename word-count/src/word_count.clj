(ns word-count)

(defn word-count
  "Returns a map of word frequencies."
  [phrase]
  (let [lowercase (clojure.string/lower-case phrase)
        tokens (clojure.string/split lowercase #"[^\w]")]
    (frequencies (filter #(not= "" %) tokens))))
