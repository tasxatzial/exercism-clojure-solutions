(ns hamming)

(defn distance
  "Returns the hamming distance between two DNA strands."
  [strand1 strand2]
  (if (= (count strand1) (count strand2))
    (count (filter true? (map not= strand1 strand2)))
    (throw (IllegalArgumentException. "strands must be of equal length"))))
