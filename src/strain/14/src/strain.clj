(ns strain)

;; lazy

(defn retain
  "Keeps the items in coll for which (pred item) returns true."
  [pred coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (pred item)
          (cons item (retain pred rest-items))
          (retain pred rest-items))))))

(defn discard
  "Removes the items in coll for which (pred item) returns true."
  [pred coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (pred item)
          (discard pred rest-items)
          (cons item (discard pred rest-items)))))))