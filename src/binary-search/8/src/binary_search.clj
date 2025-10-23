(ns binary-search)

(defn search-for
  "Returns the index of num in coll, or -1 if num is not found."
  [num coll]
  (loop [low-idx 0
         high-idx (dec (count coll))]
    (if (> low-idx high-idx)
      -1
      (let [mid-index (quot (+ high-idx low-idx) 2)
            mid-item (get coll mid-index)]
        (cond
          (= num mid-item) mid-index
          (> mid-item num) (recur low-idx (dec mid-index))
          :else (recur (inc mid-index) high-idx))))))
