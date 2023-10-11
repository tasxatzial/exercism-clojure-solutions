(ns sublist)

;; Solution 3
;; Unlike solution 2, which works with either vectors or lists,
;; this solution assumes that the input is always a vector.
;; As a result, it performs better than solution 2.

(defn sublist?
  "Returns true if l1 is a sublist of l2, else false."
  [l1 l2]
  (let [l1c (count l1)
        l2c (count l2)]
    (if (zero? l2c)
      (zero? l1c)
      (loop [i-l1 0
             i-l2 0]
        (if (= i-l2 (inc (- l2c l1c)))
          false
          (if (= i-l1 l1c)
            true
            (if (= (get l1 i-l1) (get l2 (+ i-l1 i-l2)))
              (recur (inc i-l1) i-l2)
              (recur 0 (inc i-l2)))))))))

(defn classify
  [l1 l2]
  (if (= l1 l2)
    :equal
    (if (< (count l1) (count l2))
      (if (sublist? l1 l2)
        :sublist
        :unequal)
      (if (sublist? l2 l1)
        :superlist
        :unequal))))
