(ns sublist)

;; Solution 3
;; Unlike solution 2, which works with either vectors or lists,
;; this solution assumes that the input is always a vector.
;; As a result, it performs better than solution 2.

(defn sublist?
  "Returns true if coll1 is a sublist of coll2, else false."
  [coll1 coll2]
  (let [coll1c (count coll1)
        coll2c (count coll2)]
    (if (zero? coll2c)
      (zero? coll1c)
      (loop [i-coll1 0
             i-coll2 0]
        (if (= i-coll2 (inc (- coll2c coll1c)))
          false
          (if (= i-coll1 coll1c)
            true
            (if (= (get coll1 i-coll1) (get coll2 (+ i-coll1 i-coll2)))
              (recur (inc i-coll1) i-coll2)
              (recur 0 (inc i-coll2)))))))))

(defn classify
  "Returns:
  :equal if coll1 equals coll2,
  :superlist if coll1 is a superlist of coll2,
  :sublist if coll1 is a sublist of coll2,

  If none of these conditions is true, it returns :unequal."
  [coll1 coll2]
  (if (= coll1 coll2)
    :equal
    (if (< (count coll1) (count coll2))
      (if (sublist? coll1 coll2)
        :sublist
        :unequal)
      (if (sublist? coll2 coll1)
        :superlist
        :unequal))))