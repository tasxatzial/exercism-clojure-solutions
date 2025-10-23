(ns robot-simulator)

(defn robot
  "Creates a robot at the given coordinates, facing the given direction."
  [coordinates direction]
  {:coordinates coordinates
   :bearing direction})

(def turn-right->
  {:east :south
   :south :west
   :west :north
   :north :east})

(def turn-left->
  {:east :north
   :north :west
   :west :south
   :south :east})

(def move-direction->update-vector
  {:north [:y inc]
   :east [:x inc]
   :west  [:x dec]
   :south [:y dec]})

(defn turn-left
  [robot]
  (update robot :bearing turn-left->))

(defn turn-right
  [robot]
  (update robot :bearing turn-right->))

(defn advance
  [robot]
  (let [[k f] (-> :bearing robot move-direction->update-vector)]
    (update-in robot [:coordinates k] f)))

(defn execute-instruction
  [robot instruction]
  (case instruction
    \L (turn-left robot)
    \R (turn-right robot)
    \A (advance robot)))

(defn simulate
  "Simulates the robot's movements based on the given instructions
  and updates its state."
  [instructions robot]
  (reduce execute-instruction robot instructions))
