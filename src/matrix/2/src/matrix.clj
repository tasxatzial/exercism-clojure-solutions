(ns matrix
  (:require [clojure.string :as str]))

(defn get-row
  "Returns the i-th row of the matrix."
  [matrix i]
  (let [lines (str/split-lines matrix)
        row (str/split (get lines (dec i)) #" ")]
    (map Integer/parseInt row)))

(defn get-column
  "Returns the i-th column of the matrix."
  [matrix i]
  (let [lines (str/split-lines matrix)
        matrix-items (map #(str/split % #" ") lines)
        column (map #(get % (dec i)) matrix-items)]
    (map Integer/parseInt column)))
