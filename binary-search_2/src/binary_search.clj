(ns binary-search)

;; Recursively search in a smaller sublist. Requires only one index.

(defn middle
  [coll]
  (quot (count coll) 2))

(defn search-for
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
