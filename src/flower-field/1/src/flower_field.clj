(ns flower-field
  (:require [clojure.string :as str]))

(def ^:private neighbor-diffs
  [[1 0] [-1 0] [0 1] [0 -1] [-1 -1] [1 1] [1 -1] [-1 1]])

(defn get-neighbors
  [board pos]
  (map #(get-in board (map + pos %)) neighbor-diffs))

(defn count-adjacent-flowers
  [board pos]
  (->> (get-neighbors board pos)
       (filter #{\*})
       count))

(defn update-value
  [board pos]
  (let [value (get-in board pos)]
    (if (= \space value)
      (let [adjacent-flower-count (count-adjacent-flowers board pos)]
        (if (zero? adjacent-flower-count)
          \space
          adjacent-flower-count)) 
      value)))

(defn create-grid
  [rows cols]
  (for [x (range rows)
        y (range cols)]
    [x y]))

(defn draw
   "Fills in the number of adjacent flowers for each empty square in the board."
  [board]
  (let [split-board (str/split-lines board)
        rows (count split-board)
        cols (count (first split-board))]
    (->> (create-grid rows cols)
         (map #(update-value split-board %))
         (partition cols)
         (map #(apply str %))
         (str/join "\n"))))
