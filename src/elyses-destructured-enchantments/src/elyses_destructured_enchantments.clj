(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [deck]
  (let [[f] deck]
    f))

(defn second-card
  "Returns the second card from deck."
  [deck]
  (let [[_ s] deck]
    s))

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [deck]
  (let [[f s & rest-cards] deck]
    (into rest-cards [f s])))

(defn discard-top-card
  "Returns a vector containing the first card and
   a list of the remaining cards in the deck."
  [deck]
  (let [[f & rest-cards] deck]
    [f rest-cards]))

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (if (empty? deck)
    face-cards
    (let [[f & rest-cards] deck]
      (cons f (concat face-cards rest-cards)))))
