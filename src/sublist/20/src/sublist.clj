(ns sublist)

;; Solution 1
;; Naive approach. Readable, but inefficient!

(defn sublist?
  "Returns true if coll1 is a sublist of coll2, else false."
  [coll1 coll2]
  (if (seq coll2)
    (->> (partition (count coll1) 1 coll2)
         (some #{coll1})
         some?)
    (not (seq coll1))))

(defn classify
  "Returns:
  :equal if coll1 equals coll2,
  :superlist if coll1 is a superlist of coll2,
  :sublist if coll1 is a sublist of coll2,

  If none of these conditions is true, it returns :unequal."
  [coll1 coll2]
  (cond
    (= coll1 coll2) :equal
    (sublist? coll1 coll2) :sublist
    (sublist? coll2 coll1) :superlist
    :else :unequal))