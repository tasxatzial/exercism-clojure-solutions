(ns binary-search)

(defn search-for
  ([n coll]
   (search-for n (vec coll) 0 (dec (count coll))))
  ([n coll low-index high-index]
   (if (> low-index high-index)
     (throw (Exception. "not found"))
     (let [mid-index (Math/round (Math/floor (/ (+ high-index low-index) 2)))
           mid-item (get coll mid-index)]
       (cond
         (= n mid-item) mid-index
         (> mid-item n) (recur n coll low-index (dec mid-index))
         :else (recur n coll (inc mid-index) high-index))))))

(defn middle
  [coll]
  (Math/round (Math/floor (/ (count coll) 2))))
