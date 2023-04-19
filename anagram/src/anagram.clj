(ns anagram
  (:require [clojure.string :as str]))

(defn- _anagram?
  "Returns true if candidate is an anagram of word. Assumes word is in lowercase."
  [word word-freqs candidate]
  (let [lowercase-candidate (str/lower-case candidate)]
    (and (not= word lowercase-candidate)
         (= word-freqs (frequencies lowercase-candidate)))))

(defn anagrams-for
  [word candidates]
  (let [lowercase-word (str/lower-case word)
        lowercase-word-freqs (frequencies lowercase-word)
        word-anagram? (partial _anagram? lowercase-word lowercase-word-freqs)]
    (filter word-anagram? candidates)))
