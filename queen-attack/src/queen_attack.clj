(ns queen-attack)

(def empty-board
  (apply str (take 8 (repeat "_ _ _ _ _ _ _ _\n"))))

(defn vec-pos
  "Transforms a (x,y) board position to the corresponding
  vector position."
  [x y]
  (+ (* 16 x) (* 2 y)))

(defn board-string
  "Places white and black queens on the board in the
  positions specified by the given map."
  [positions]
  (if (seq positions)
    (let [[wx wy] (:w positions)
          [bx by] (:b positions)
          board (vec empty-board)
          w-board (assoc board (vec-pos wx wy) \W)
          wb-board (assoc w-board (vec-pos bx by) \B)]
      (apply str wb-board))
    empty-board))

(defn can-attack
  "Returns true if the queens in the positions specified
  by the given map can attack each other, false otherwise."
  [positions]
  (let [[wx wy] (:w positions)
        [bx by] (:b positions)]
    (or (= wx bx) (= wy by) (= (- wx wy) (- bx by)))))
