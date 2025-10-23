(ns grains)

(defn square
  "Returns the number of grains in square n (1 <= n <= 64)."
  [n]
  (reduce *' (take (dec n) (repeat 2))))

(defn total
  "Returns the total number of grains."
  []
  (dec (square 65)))
