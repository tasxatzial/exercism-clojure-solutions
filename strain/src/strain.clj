(ns strain)

(defn retain
  [f coll]
  (reduce (fn [result el]
            (if (f el)
              (conj result el)
              result))
          []
          coll))

(defn discard
  [f coll]
  (reduce (fn [result el]
            (if (f el)
              result
              (conj result el)))
          []
          coll))
