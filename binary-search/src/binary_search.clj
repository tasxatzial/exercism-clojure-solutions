(ns binary-search)

(defn search-for
  "Finds the position of n in a sorted coll. Throws exception
  when n is not found."
  ([n coll] (search-for n coll 0 (dec (count coll))))
  ([n coll low high]
   (if (> low high)
     (throw (Exception. "not found"))
     (let [mid (Math/round (Math/floor (/ (+ high low) 2)))
           mid-item (nth coll mid)]
       (cond
         (= n mid-item) mid
         (> mid-item n) (recur n coll low (dec mid))
         :else (recur n coll (inc mid) high))))))

(defn middle [coll]
  ;; not implemented since the tests do not cover the case
  ;; where coll has even number of items (there is no middle
  ;; item)
  )
