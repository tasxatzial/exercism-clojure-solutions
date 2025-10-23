(ns darts
  (:require [clojure.math :as math]))

(defn distance-to-center
  [x y]
  (math/sqrt (+ (* x x) (* y y))))

(defn score
  "Calculates the score of a dart throw"
  [x y]
  (condp >= (distance-to-center x y)
    1 10
    5 5
    10 1
    0))
