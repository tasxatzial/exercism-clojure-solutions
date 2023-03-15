(ns sublist)

(defn sublist?
  "Returns true if l2 is a sublist of l1, else false."
  [l1 l2]
  (->> (partition (count l2) 1 l1)
       (some #(= l2 %))
       true?))

(defn classify
  [l1 l2]
  (cond
    (= l1 l2) :equal
    (sublist? l2 l1) :sublist
    (sublist? l1 l2) :superlist
    :else :unequal))
