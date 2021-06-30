(ns proverb)

(defn recite
  "Generates the relevant proverb from the given list of words."
  [s]
  (if (seq s)
    (let [first-s (mapv #(str "For want of a " %1 " the " %2 " was lost.")
                        s (rest s))
          last-s (str "And all for the want of a " (first s) ".")]
      (clojure.string/join "\n" (conj first-s last-s)))
    ""))
