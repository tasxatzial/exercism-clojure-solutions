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
    (into [s f] rest-cards)))

;; this function does not do what its name implies
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
  (let [[f] deck]
    (if f
      (into (into [f] face-cards) (rest deck))
      face-cards)))
