(ns anagram
  (:require [clojure.string :as str]))

(defn- _anagram?
  "Returns true if target is an anagram of src. Assumes src
  is in lower-case."
  [lowercase-src target]
  (let [lowercase-target (str/lower-case target)]
    (and (not= lowercase-src target)
         (= (frequencies lowercase-src) (frequencies lowercase-target)))))

(defn anagrams-for
  [word candidates]
  (let [lowercase-word (str/lower-case word)
        word-anagram? (partial _anagram? lowercase-word)]
    (filter word-anagram? candidates)))
