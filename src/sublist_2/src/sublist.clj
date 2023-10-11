(ns sublist)

;; Solution 2
;; This approach employs a similar concept to solutions that utilize 'partition'.
;; However, there are two differences:
;; 1) The classify function calls sublist? only once.
;; 2) It never constructs any subsequences.
;; As a result, it is way more efficient.

(defn sub-equals?
  "Returns true if l1 equals the list formed by the first (count l1)
  elements of l2."
  [l1 l2]
  (if-let [l1-seq (seq l1)]
    (if (= (first l1-seq) (first l2))
      (recur (rest l1-seq) (rest l2))
      false)
    true))

(defn sublist?
  "Returns true if l1 is a sublist of l2, else false."
  [l1 l2]
  (let [l1c (count l1)]
    (if (zero? l1c)
      true
      (loop [l2 l2
             l2c (count l2)]
        (if (<= l1c l2c)
          (or (sub-equals? l1 l2)
              (recur (rest l2) (dec l2c)))
          false)))))

(defn classify
  [l1 l2]
  (if (= l1 l2)
    :equal
    (if (< (count l1) (count l2))
      (if (sublist? l1 l2)
        :sublist
        :unequal)
      (if (sublist? l2 l1)
        :superlist
        :unequal))))
