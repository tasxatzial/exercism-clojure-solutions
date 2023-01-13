(ns strain)

(defn retain
  [f coll]
  (loop [result []
         coll coll]
    (if (seq coll)
      (let [[item & rest-items] coll]
        (if (true? (f item))
          (recur (conj result item) rest-items)
          (recur result rest-items)))
      result)))

(defn discard
  [f coll]
  (loop [result []
         coll coll]
    (if (seq coll)
      (let [[item & rest-items] coll]
        (if (true? (f item))
          (recur result rest-items)
          (recur (conj result item) rest-items)))
      result)))
