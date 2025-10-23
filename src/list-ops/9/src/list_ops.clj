; with vectors
; the optional goal is in the previous iteration

(ns list-ops)

(declare foldl)
(declare reverse-order)

(defn append
  "Given two vectors, it adds all the items in the second vector to the end of the first vector"
  [coll1 coll2]
  (foldl conj coll2 coll1))

(defn concatenate
  "Given a vector of vectors, it combines all the vectors into one flattened vector"
  [colls]
  (foldl append colls []))

(defn select-if
  "Given a predicate and a vector, it returns the vector of all items for which predicate(item) is true"
  [pred coll]
  (let [reducer (fn [acc el]
                  (if (pred el)
                    (conj acc el)
                    acc))]
    (foldl reducer coll [])))

(defn length
  "Given a vector, it returns the number of items within it"
  [coll]
  (let [reducer (fn [acc _]
                  (inc acc))]
    (foldl reducer coll 0)))

(defn apply-to-each
  "Given a function and a vector, it returns the vector of the results of applying function(item) on all items"
  [f coll]
  (let [reducer (fn [acc el]
                  (conj acc (f el)))]
    (foldl reducer coll [])))

(defn foldl
  "Given a function, a vector, and initial accumulator, it folds (reduces) each item into the accumulator from the left"
  [f coll acc]
  (if (seq coll)
    (recur f (rest coll) (f acc (first coll)))
    acc))

(defn foldr
  "Given a function, a vector, and an initial accumulator, it folds (reduces) each item into the accumulator from the right"
  [f coll acc]
  (foldl f (reverse-order coll) acc))

(defn reverse-order
  "Given a vector, it returns a vector with all the original items, but in reverse order"
  [coll]
  (loop [index (dec (length coll))
         result []]
    (if (neg? index)
      result
      (recur (dec index) (conj result (coll index))))))