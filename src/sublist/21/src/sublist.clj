(ns sublist)

;; Solution 2
;; This approach employs a similar concept to solutions that utilize 'partition'.
;; However, there are two improvements that make it much faster:
;; 1) The classify function calls sublist? only once.
;; 2) It never constructs any subsequences.

(defn- sub-equals?
  "Returns true if coll1 equals the list formed by the first (count coll1)
  elements of coll2."
  [coll1 coll2]
  (if-let [coll1-seq (seq coll1)]
    (if (= (first coll1-seq) (first coll2))
      (recur (rest coll1-seq) (rest coll2))
      false)
    true))

(defn sublist?
  "Returns true if coll1 is a sublist of coll2, else false."
  [coll1 coll2]
  (let [coll1c (count coll1)]
    (if (zero? coll1c)
      true
      (loop [coll2 coll2
             coll2c (count coll2)]
        (if (<= coll1c coll2c)
          (or (sub-equals? coll1 coll2)
              (recur (rest coll2) (dec coll2c)))
          false)))))

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