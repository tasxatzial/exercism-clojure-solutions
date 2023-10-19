(ns card-games)

(def Jack 11)

(defn rounds
  "Takes the current round number and returns 
   a `list` with that round and the _next two_."
  [n]
  (list n (inc n) (+ n 2)))

(defn concat-rounds 
  "Takes two lists and returns a single `list` 
   consisting of all the rounds in the first `list`, 
   followed by all the rounds in the second `list`"
  [l1 l2]
  (apply list (concat l1 l2)))

(defn contains-round? 
  "Takes a list of rounds played and a round number.
   Returns `true` if the round is in the list, `false` if not."
  [l n]
  (boolean (some #{n} l)))

(defn card-average
  "Returns the average value of a hand"
  [hand]
  (float (/ (apply + hand) (count hand))))

(defn card-median
  "Returns the median value of a hand.
  Assumes hand contains an odd number of cards."
  [hand]
  (nth hand (/ (count hand) 2)))

(defn approx-average?
  "Returns `true` if average is equal to either one of:
  - Take the average of the _first_ and _last_ number in the hand.
  - Using the median (middle card) of the hand."
  [hand]
  (let [avg (card-average hand)
        median (card-median hand)
        avg-first-last (/ (+ (first hand) (last hand)) 2)]
    (or (== avg median) (== avg avg-first-last))))

(defn average-even-odd?
  "Returns true if the average of the cards at even indexes 
   is the same as the average of the cards at odd indexes."
  [hand]
  (let [even-indices-avg (card-average (take-nth 2 hand))
        odd-indices-avg (card-average (take-nth 2 (rest hand)))]
    (= even-indices-avg odd-indices-avg)))

(defn maybe-double-last
  "If the last card is a Jack (11), doubles its value
   before returning the hand."
  [hand]
  (if (= Jack (last hand))
    (apply list (concat (drop-last hand) [(* 2 Jack)]))
    hand))
