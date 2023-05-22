(ns sublist)

;; This approach employs a similar concept to solutions that utilize 'partition'.
;; However, there are two differences:
;; 1) The classify function calls sublist? only once.
;; 2) It never constructs any subsequences.
;; As a result, it exhibits a speed improvement of 3-4 orders of magnitude.

(defn- p-equals?
  "Returns true if all elements of l1 match the corresponding elements of l2."
  [l1 l2]
  (if-let [l1-seq (seq l1)]
    (if (= (first l1-seq) (first l2))
      (recur (rest l1-seq) (rest l2))
      false)
    true))

(defn sublist?
  "Returns true if l1 is a sublist of l2, else false."
  [l1 l2]
  (let [cl1 (count l1)]
    (if (zero? cl1)
      true
      (loop [l2 l2
             cl2 (count l2)]
        (if (<= cl1 cl2)
          (or (p-equals? l1 l2)
              (recur (rest l2) (dec cl2)))
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
