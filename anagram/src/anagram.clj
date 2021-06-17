(ns anagram)

(defn anagrams-for
  "Selects the correct sublist of anagrams, given a word and a
  list of possible anagrams."
  [word prospect-list]
  (let [word-lower (clojure.string/lower-case word)
        word-freq (frequencies word-lower)]
    (reduce (fn [result prospect]
              (let [prospect-lower (clojure.string/lower-case prospect)]
                (if (and (not= word-lower prospect-lower)
                         (= word-freq (frequencies prospect-lower)))
                  (conj result prospect)
                  result)))
            []
            prospect-list)))
