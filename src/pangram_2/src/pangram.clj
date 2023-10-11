(ns pangram)

(def alphabet "abcdefhijklmnopqrstuvwxyz")

(defn pangram? [sentence]
  (-> (clojure.string/lower-case sentence)
      set
      (every? alphabet)))
