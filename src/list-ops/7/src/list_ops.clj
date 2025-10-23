;; optional goal with lists

(ns list-ops)

(declare foldl)
(declare reverse-order)

(defn append
  "Given two lists, it adds all the items in the second list to the end of the first list"
  [coll1 coll2]
  (reverse-order (foldl conj coll2 (reverse-order coll1))))

(defn concatenate
  "Given a list of lists, it combines all the lists into one flattened list"
  [colls]
  (foldl append colls ()))

(defn select-if
  "Given a predicate and a list, it returns the list of all items for which predicate(item) is true"
  [pred coll]
  (let [reducer (fn [acc el]
                  (if (pred el)
                    (conj acc el)
                    acc))]
    (reverse-order (foldl reducer coll ()))))

(defn length
  "Given a list, it returns the number of items within it"
  [coll]
  (let [reducer (fn [acc _]
                  (inc acc))]
    (foldl reducer coll 0)))

(defn apply-to-each
  "Given a function and a list, it returns the list of the results of applying function(item) on all items"
  [f coll]
  (let [reducer (fn [acc el]
                  (conj acc (f el)))]
    (reverse-order (foldl reducer coll ()))))

(defn foldl
  "Given a function, a list, and initial accumulator, it folds (reduces) each item into the accumulator from the left"
  [f coll acc]
  (if (seq coll)
    (recur f (rest coll) (f acc (first coll)))
    acc))

(defn foldr
  "Given a function, a list, and an initial accumulator, it folds (reduces) each item into the accumulator from the right"
  [f coll acc]
  (foldl f (reverse-order coll) acc))

(defn reverse-order
  "Given a list, it returns a list with all the original items, but in reverse order"
  [coll]
  (foldl conj coll ()))