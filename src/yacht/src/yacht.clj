(ns yacht)

(defn sort-dice-freqs
  [dice]
  (->> dice
       frequencies
       (sort-by second >)))

(defn sum-roll
  [roll dice]
  (reduce + (filter #{roll} dice)))

(defn sum-dice
  [dice]
  (reduce + dice))

(defn score-yacht
  [dice]
  (if (apply = dice)
    50
    0))

(defn score-quads
  [dice]
  (let [sorted-dice-freqs (sort-dice-freqs dice)
        [roll freq] (first sorted-dice-freqs)]
    (if (>= freq 4)
      (* 4 roll)
      0)))

(defn score-full-house
  [dice]
  (let [sorted-dice-freqs (sort-dice-freqs dice)
        [[roll1 freq1] [roll2 freq2]] sorted-dice-freqs]
    (if (and (= 3 freq1) (= 2 freq2))
      (+ (* roll1 3) (* roll2 2))
      0)))

(defn score-little-straight
  [dice]
  (if (= [1 2 3 4 5] (sort dice))
    30
    0))

(defn score-big-straight
  [dice]
  (if (= [2 3 4 5 6] (sort dice))
    30
    0))

(def type->score-fn
  {"ones"            (partial sum-roll 1)
   "twos"            (partial sum-roll 2)
   "threes"          (partial sum-roll 3)
   "fours"           (partial sum-roll 4)
   "fives"           (partial sum-roll 5)
   "sixes"           (partial sum-roll 6)
   "choice"          sum-dice
   "yacht"           score-yacht
   "four of a kind"  score-quads
   "full house"      score-full-house
   "little straight" score-little-straight
   "big straight"    score-big-straight})

(defn score
  [dices type]
  ((type->score-fn type) dices))
