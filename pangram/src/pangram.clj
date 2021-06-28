(ns pangram)

(defn pangram?
  "Returns true if s is a pangram, false otherwise."
  [s]
  (= 26 (->> s
             clojure.string/lower-case
             (filter #(<= 97 (int %) 122))
             set
             count)))
