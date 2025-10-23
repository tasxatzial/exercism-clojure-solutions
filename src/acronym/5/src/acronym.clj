(ns acronym)

(defn acronym
  "Converts phrase to its acronym."
  [phrase]
  (->> (re-seq #"(?<=^|[-_ ])[A-Za-z]" phrase)
       (map first)
       (apply str)
       clojure.string/upper-case))