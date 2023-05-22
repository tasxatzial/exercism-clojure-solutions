(ns sublist)

(defn- sublist?
  "Returns true if l1 is a sublist of l2, else false. Assumes l2 is not empty."
  [l1 l2]
  (->> (partition (count l1) 1 l2)
       (some #(= l1 %))
       true?))

(defn classify
  [l1 l2]
  (cond
    (= l1 l2) :equal
    (sublist? l2 l1) :superlist
    (sublist? l1 l2) :sublist
    :else :unequal))
