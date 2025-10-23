(ns difference-of-squares)

;; solution 1

(defn square
  [num]
  (* num num))

(defn square-of-sum
  "Returns the square of the sum of the numbers up to N."
  [N]
  (->> (range 1 (inc N))
       (reduce +')
       square))

(defn sum-of-squares
  "Returns the sum of the squares of the numbers up to N."
  [N]
  (transduce (map square) +' (range 1 (inc N))))

(defn difference
  "Returns the difference between the square of the sum
  and the sum of the squares of the numbers up to N."
  [N]
  (- (square-of-sum N) (sum-of-squares N)))
