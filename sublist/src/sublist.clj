(ns sublist)

;; Solution 1
;; Naive approach. Inefficient!

(defn sublist?
  "Returns true if l1 is a sublist of l2, else false."
  [l1 l2]
  (if (seq l2)
    (->> (partition (count l1) 1 l2)
         (some #{l1})
         some?)
    (not (seq l1))))

(defn classify
  [l1 l2]
  (cond
    (= l1 l2) :equal
    (sublist? l1 l2) :sublist
    (sublist? l2 l1) :superlist
    :else :unequal))
