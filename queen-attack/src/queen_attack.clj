(ns queen-attack)

(def empty-board
  (apply str (take 8 (repeat "_ _ _ _ _ _ _ _\n"))))

(defn get-board-index
  "Transforms a (x,y) board position to the corresponding
  one dimensional index. Both x and y take values from
  0 to 7."
  [x y]
  (+ (* 16 x) (* 2 y)))

(defn board-string
  "Places white and black queens on the board using the
  given positions map."
  [positions]
  (if (empty? positions)
    empty-board
    (let [[wx wy] (:w positions)
          [bx by] (:b positions)]
      (apply str (-> (vec empty-board)
                     (assoc (get-board-index wx wy) \W)
                     (assoc (get-board-index bx by) \B))))))

(defn can-attack
  "Returns true if the queens in the given positions map can
  attack each other, else false."
  [positions]
  (let [[wx wy] (:w positions)
        [bx by] (:b positions)]
    (or (= wx bx)
        (= wy by)
        (= (Math/abs ^int (- wx bx)) (Math/abs ^int (- wy by))))))
