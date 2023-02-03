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

(defn coordinate-update
  "Accepts a moving direction and returns a vector of:
  1. The coordinate keyword that needs to be updated.
  2. The function that will be used to update the corresponding coordinate."
  [direction]
  (case direction
    :north [:y inc]
    :east [:x inc]
    :west  [:x dec]
    :south [:y dec]))

(defn move
  "Move the robot by 1 in the direction it's facing."
  [robot]
  (let [direction (robot :bearing)
        [coordinate-key advance-fn] (coordinate-update direction)]
    (update-in robot [:coordinates coordinate-key] advance-fn)))

(defn simulate
  [instructions robot]
  (reduce (fn [robot instruction]
            (case instruction
              \L (update robot :bearing turn-left)
              \R (update robot :bearing turn-right)
              \A (move robot)))
          robot
          instructions))
