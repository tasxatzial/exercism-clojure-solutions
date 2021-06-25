(ns accumulate)

(defn accumulate
  "Given a collection and an operation to perform on
  each element of the collection, returns a new collection
  containing the result of applying that operation to each
  element of the input collection."
  [f coll]
  (loop [result []
         [item & more] coll]
    (if item
      (recur (conj result (f item))
             more)
      result)))
