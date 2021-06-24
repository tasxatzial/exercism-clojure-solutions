(ns strain)

(defn retain
  "Returns a new collection containing those elements
  where the predicate f is true."
  [f coll]
  (reduce (fn [result item]
            (if (f item)
              (conj result item)
              result))
          []
          coll))

(defn discard
  "Returns a new collection containing those elements
  where the predicate f is false."
  [f coll]
  (reduce (fn [result item]
            (if (f item)
              result
              (conj result item)))
          []
          coll))
