(ns grains)

;; Calculates powers of 2 in O(logn)

(def sqr #(*' % %))
(def mult2 #(*' % 2))

(defn- power
  "Returns 2^|n|. n must be integer and != 0, 1, -1"
  [^long n]
  (loop [result []
         num (Math/abs n)]
    (cond
      (= 1 num) (reduce #(%2 %1) 2 (rseq result))
      (even? num) (recur (conj result sqr) (/ num 2))
      :else (recur (conj result mult2) (dec num)))))

(defn pow2n
  "Returns 2^n. n must be integer otherwise it returns nil."
  [n]
  (if (not (int? n))
    nil
    (case n
      0 1
      1 2
      -1 1/2
      (if (neg? n)
        (/ 1 (power n))
        (power n)))))

(defn square
  "Returns the number of grains in square n."
  [n]
  (pow2n (dec n)))

(defn total
  "Returns the total number of grains."
  []
  (dec (square 65)))
