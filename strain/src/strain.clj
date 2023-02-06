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

;; lazy retain
(defn retain-lazy
  [f coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (f item)
          (cons item (retain-lazy f rest-items))
          (retain-lazy f rest-items))))))

;; lazy discard
(defn discard-lazy
  [f coll]
  (lazy-seq
    (when (seq coll)
      (let [[item & rest-items] coll]
        (if (f item)
          (discard-lazy f rest-items)
          (cons item (discard-lazy f rest-items)))))))
