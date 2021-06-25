(ns difference-of-squares)

(defn sum-of-squares
  "Returns the sum of squares from 1 to N."
  [N]
  (apply + (map #(* % %)
                (range 1 (inc N)))))

(defn square-of-sum
  "Returns the square of the sum from 1 to N."
  [N]
  (let [sum (apply + (range 1 (inc N)))]
    (* sum sum)))

(defn difference
  "Returns the difference between the square of the
  sum and the sum of the squares of the first N
  natural numbers."
  [N]
  (- (square-of-sum N) (sum-of-squares N)))
