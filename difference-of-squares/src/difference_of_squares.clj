(ns difference-of-squares)

(defn square
  [x]
  (* x x))

(defn sum-of-squares
  [N]
  (->> N
       inc
       (range 1)
       (map square)
       (apply +)))

(defn square-of-sum
  [N]
  (->> N
       inc
       (range 1)
       (apply +)
       square))

(defn difference
  [N]
  (- (square-of-sum N) (sum-of-squares N)))

;; solution 2: closed-form formulas
(defn sum-of-squares2
  [N]
  (-> N
      (* (inc N))
      (* (inc (* 2 N)))
      (/ 6)))

(defn square-of-sum2
  [N]
  (-> N
      (* (inc N))
      (/ 2)
      square))
