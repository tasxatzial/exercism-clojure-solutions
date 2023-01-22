(ns binary-search)

(defn middle
  [coll]
  (quot (count coll) 2))

;; solution 1:
;; Convert to vector and recusively update low & high indices.
(defn search-for
  [n coll]
  (let [coll (vec coll)]
    (loop [low-idx 0
           high-idx (dec (count coll))]
      (if (> low-idx high-idx)
        (throw (Exception. "not found"))
        (let [mid-index (quot (+ high-idx low-idx) 2)
              mid-item (get coll mid-index)]
          (cond
            (= n mid-item) mid-index
            (> mid-item n) (recur low-idx (dec mid-index))
            :else (recur (inc mid-index) high-idx)))))))

;; solution 2:
;; Recursively search in a smaller sublist. Requires only one index.
(defn search-for2
  [key coll]
  (loop [coll-idx 0
         subcoll coll]
    (if (empty? subcoll)
      (throw (Exception. "not found"))
      (let [subcoll-mid-idx (middle subcoll)
            subcoll-mid-el (nth subcoll subcoll-mid-idx)]
        (cond
          (= key subcoll-mid-el)
            (+ coll-idx subcoll-mid-idx)
          (< key subcoll-mid-el)
            (recur coll-idx (take subcoll-mid-idx subcoll))
          :else (recur (+ coll-idx (inc subcoll-mid-idx))
                       (drop (inc subcoll-mid-idx) subcoll)))))))
