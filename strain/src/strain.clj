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

;; lazy retain
(defn retain-lazy
  [f coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (true? (f item))
          (cons item (retain-lazy f rest-items))
          (retain-lazy f rest-items))))))

;; lazy discard
(defn discard-lazy
  [f coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (true? (f item))
          (discard-lazy f rest-items)
          (cons item (discard-lazy f rest-items)))))))
