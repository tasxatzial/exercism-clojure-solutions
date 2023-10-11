(ns acronym)

(defn acronym
  [s]
  (->> s
       (re-seq #"\b\p{L}|(?<=\p{Ll})\p{Lu}|(?<=-)\p{L}")
       (apply str)
       clojure.string/upper-case))
