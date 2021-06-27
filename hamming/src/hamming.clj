(ns hamming)

(defn distance
  "Calculate the Hamming Distance between two DNA strands"
  [strand1 strand2]
  (when (= (count strand1) (count strand2))
    (reduce (fn [result x]
              (if (false? x)
                (inc result)
                result))
            0
            (map = strand1 strand2))))
