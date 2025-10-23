(ns scrabble-score)

(defn letter->score
  [l]
  (case l
    (\a \e \i \o \u \l \n \r \s \t) 1
    (\d \g) 2
    (\b \c \m \p) 3
    (\f \h \v \w \y) 4
    \k 5
    (\j \x) 8
    (\q \z) 10))

(defn score-word
  "Returns the scrabble score of a word."
  [word]
  (->> word
       clojure.string/lower-case
       (map letter->score)
       (reduce +)))