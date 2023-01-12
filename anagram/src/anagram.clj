(ns anagram)

(defn anagram?
  "Returns true if target is an anagram of src. Assumes src
  is in lower-case."
  [src target]
  (let [lowercase-target (clojure.string/lower-case target)]
    (and (not= src target)
         (= (frequencies src) (frequencies lowercase-target)))))

(defn anagrams-for
  [word candidates]
  (let [lowercase-word (clojure.string/lower-case word)
        word-anagram? (partial anagram? lowercase-word)]
    (filter word-anagram? candidates)))
