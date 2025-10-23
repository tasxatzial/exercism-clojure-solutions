(ns eliuds-eggs)

;; standard loop approach
(defn egg-count
  "Returns the number of 1 bits in the binary representation of the given number."
  [num]
  (loop [n num
         eggs 0]
    (if (zero? n)
      eggs
      (recur (quot n 2) (+ eggs (rem n 2))))))

;; higher-level functions approach
(defn egg-count
  "Returns the number of 1 bits in the binary representation of the given number."
  [num]
  (->> num
       (iterate #(quot % 2)) ;; or (iterate #(bit-shift-right % 1))
       (take-while pos?)
       (map #(rem % 2)) ;; or (map #(bit-and % 1))
       (apply +)))
