(ns pangram)

(defn pangram? [sentence]
  (->> (clojure.string/lower-case sentence)
       (re-seq #"[a-z]")
       set
       count
       (= 26)))
