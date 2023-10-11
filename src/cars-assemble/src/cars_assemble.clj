(ns cars-assemble)

(def production-speed->success-rate
  {0 0,
   1 1, 2 1, 3 1, 4 1,
   5 0.9, 6 0.9, 7 0.9, 8 0.9,
   9 0.8,
   10 0.77})

(defn get-produced-cars
  "Returns the total produced cards per hour."
  [speed]
  (* 221.0 speed))

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate."
  [speed]
  (let [success-rate (production-speed->success-rate speed)
        produced-cars (get-produced-cars speed)]
    (* success-rate produced-cars)))

(defn working-items
  "Calculates how many working cars are produced per minute."
  [speed]
  (-> speed
      production-rate
      (/ 60)
      Math/floor
      Math/round))
