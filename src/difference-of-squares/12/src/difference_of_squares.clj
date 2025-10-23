(ns difference-of-squares)

;; solution 2: closed-form formulas

(defn square
  [num]
  (* num num))

(defn square-of-sum
  "Returns the square of the sum of the numbers up to N."
  [N]
  (-> (* (inc N) N)
      (/ 2)
      square))

(defn sum-of-squares
  "Returns the sum of the squares of the numbers up to N."
  [N]
  (-> (* (inc N) N)
      (* (inc (* 2 N)))
      (/ 6)))

(defn difference
  "Returns the difference between the square of the sum
  and the sum of the squares of the numbers up to N."
  [N]
  (- (square-of-sum N) (sum-of-squares N)))