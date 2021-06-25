(ns strain)

(defn retain
  "Returns a new collection containing those elements
  where the predicate f is true."
  [f coll]
  (loop [result []
         [item & more] coll]
    (if item
      (if (f item)
        (recur (conj result item) more)
        (recur result more))
      result)))

(defn discard
  "Returns a new collection containing those elements
  where the predicate f is false."
  [f coll]
  (loop [result []
         [item & more] coll]
    (if item
      (if (f item)
        (recur result more)
        (recur (conj result item) more))
      result)))
