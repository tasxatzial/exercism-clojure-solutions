(ns strain)

;; lazy

(defn retain
  [f coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (f item)
          (cons item (retain f rest-items))
          (retain f rest-items))))))

(defn discard
  [f coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (f item)
          (discard f rest-items)
          (cons item (discard f rest-items)))))))
