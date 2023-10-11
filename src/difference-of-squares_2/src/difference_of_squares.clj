(ns difference-of-squares)

;;closed-form formulas

(defn square
  [x]
  (* x x))

(defn sum-of-squares
  [N]
  (-> N
      (* (inc N))
      (* (inc (* 2 N)))
      (/ 6)))

(defn square-of-sum
  [N]
  (-> N
      (* (inc N))
      (/ 2)
      square))

(defn difference
  [N]
  (- (square-of-sum N) (sum-of-squares N)))
