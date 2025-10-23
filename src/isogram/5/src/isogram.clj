(ns isogram)

(defn isogram?
  "Returns true if the given string is an isogram;
  otherwise, it returns false."
  [s]
  (->> s
       clojure.string/lower-case
       (filter #(Character/isAlphabetic (int %)))
       frequencies
       vals
       (every? #{1})))
