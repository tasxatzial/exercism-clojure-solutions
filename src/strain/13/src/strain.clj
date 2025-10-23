(ns strain)

;; non-lazy

(defn retain
  "Keeps the items in coll for which (pred item) returns true."
  [pred coll]
  (reduce (fn [result el]
            (if (pred el)
              (conj result el)
              result))
          []
          coll))

(defn discard
  "Removes the items in coll for which (pred item) returns true."
  [pred coll]
  (reduce (fn [result el]
            (if (pred el)
              result
              (conj result el)))
          []
          coll))