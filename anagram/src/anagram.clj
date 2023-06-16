(ns anagram
  (:require [clojure.string :as str]))

(defn- anagram?
  "Returns true if candidate is an anagram of word. Assumes word is in lowercase."
  [word word-freqs candidate]
  (let [lcase-candidate (str/lower-case candidate)]
    (and (not= word lcase-candidate)
         (= word-freqs (frequencies lcase-candidate)))))

(defn anagrams-for
  [word candidates]
  (let [lcase-word (str/lower-case word)
        word-freqs (frequencies lcase-word)
        word-anagram? (partial anagram? lcase-word word-freqs)]
    (filter word-anagram? candidates)))
