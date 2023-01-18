(ns acronym)

(defn acronym
  [s]
  (->> s
       (re-seq #"\b\p{L}|\p{Ll}(\p{Lu})|-(\p{L})")
       (map #(first (filter some? (rseq %))))
       (apply str)
       clojure.string/upper-case))
