(ns minesweeper
  (:require [clojure.string :as str]))

(def line-separator (System/getProperty "line.separator"))

(def ^:private neighbor-diffs
  [[1 0] [-1 0] [0 1] [0 -1] [-1 -1] [1 1] [1 -1] [-1 1]])

(defn get-neighbors
  [board pos]
  (map #(get-in board (map + pos %)) neighbor-diffs))

(defn count-adjacent-bombs
  [board pos]
  (->> (get-neighbors board pos)
       (filter #{\*})
       count))

(defn update-value
  [board pos]
  (let [value (get-in board pos)]
    (if (= \space value)
      (let [adjacent-bomb-count (count-adjacent-bombs board pos)]
        (if (zero? adjacent-bomb-count)
          \space
          adjacent-bomb-count))
      value)))

(defn create-grid
  [rows cols]
  (for [x (range rows)
        y (range cols)]
    [x y]))

(defn draw
  [board]
  (let [split-board (str/split-lines board)
        rows (count split-board)
        cols (count (first split-board))]
    (->> (create-grid rows cols)
         (map #(update-value split-board %))
         (partition cols)
         (map #(apply str %))
         (str/join line-separator))))
