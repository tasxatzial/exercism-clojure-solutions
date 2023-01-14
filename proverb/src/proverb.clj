(ns proverb)

(defn first-proverb-sentence
  [w1 w2]
  (str "For want of a " w1 " the " w2 " was lost."))

(defn second-proverb-sentence
  [w]
  (str "And all for the want of a " w "."))

(defn recite
  [s]
  (if-let [seq-s (seq s)]
    (let [but-last-sentences (map first-proverb-sentence seq-s (rest seq-s))
          last-sentence (second-proverb-sentence (first seq-s))]
      (->> [last-sentence]
           (concat but-last-sentences)
           (clojure.string/join "\n")))
    ""))
