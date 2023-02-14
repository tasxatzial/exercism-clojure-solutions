(ns robot-simulator)

(defn robot
  [coordinates direction]
  {:coordinates coordinates
   :bearing direction})

(def turn-right
  {:east :south
   :south :west
   :west :north
   :north :east})

(def turn-left
  {:east :north
   :north :west
   :west :south
   :south :east})

(def direction->coordinate-diff
  {:north [:y inc]
   :east [:x inc]
   :west  [:x dec]
   :south [:y dec]})

(defn turn-robot-left
  [robot]
  (update robot :bearing turn-left))

(defn turn-robot-right
  [robot]
  (update robot :bearing turn-right))

(defn move-robot
  [robot]
  (let [[k f] (-> robot :bearing direction->coordinate-diff)]
    (update-in robot [:coordinates k] f)))

(def instruction->callback
  {\L turn-robot-left
   \R turn-robot-right
   \A move-robot})

(defn execute-instruction
  [robot instruction]
  ((instruction->callback instruction) robot))

(defn simulate
  [instructions robot]
  (reduce execute-instruction robot instructions))
