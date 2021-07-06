(ns spiral-matrix)

(defn init-matrix
  "Creates and initializes a nxn matrix to 0 values."
  [n]
  (let [row (vec (repeat n 0))]
    (vec (repeat n row))))

(defn empty-val?
  "Returns true if val is non zero, false otherwise."
  [val]
  (and (not= nil val) (zero? val)))

(defn next-pos
  "Returns the next position when we start
  from position [x y] and move in the specified direction."
  [[x y] direction]
  (case direction
    :right [x (inc y)]
    :down [(inc x) y]
    :left [x (dec y)]
    :up [(dec x) y]))

;; returns the direction we need to turn when
;; the next board position is occupied
(def turn-direction
  {:right :down
   :down :left
   :left :up
   :up :right})

(defn next-valid-pos
  "Returns the next free position when we start from the
  pos position and move in the specified direction.
  Returns nil if all neighbors of pos are occupied."
  ([pos direction board]
   (next-valid-pos pos direction board 0))
  ([pos direction board turned]
   (when (not= turned 4)
     (let [new-pos (next-pos pos direction)]
       (if (empty-val? (get-in board new-pos))
         [direction new-pos]
         (recur pos (turn-direction direction) board (inc turned)))))))

(defn spiral [n]
  "Returns a nxn spiral matrix, filled with values from
  1 to n*n."
  (let [board (init-matrix n)]
    (loop [pos [0 -1]
           i 1
           board board
           direction :right]
      (let [[next-direction next-pos] (next-valid-pos pos direction board)]
        (if next-pos
          (let [new-board (assoc-in board next-pos i)]
            (recur next-pos (inc i) new-board next-direction))
          board)))))
