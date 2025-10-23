(ns square-root)

(defn square-root
  "Calculates a number's square root using binary search"
  [n]
  (loop [low 0
         high (inc n)]
    (if (= low (dec high))
      low
      (let [mid (quot (+ low high) 2)]
        (if (<= (* mid mid) n)
          (recur mid high)
          (recur low mid))))))
