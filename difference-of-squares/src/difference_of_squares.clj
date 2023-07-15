(ns difference-of-squares)

(defn square
  [x]
  (*' x x))

(defn sum-of-squares
  [N]
  (transduce (map square) +' (range 1 (inc N))))

(defn square-of-sum
  [N]
  (->> (range 1 (inc N))
       (reduce +')
       square))

(defn difference
  [N]
  (- (square-of-sum N) (sum-of-squares N)))
