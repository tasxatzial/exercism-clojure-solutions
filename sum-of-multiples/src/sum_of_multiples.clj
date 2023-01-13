(ns sum-of-multiples)

(defn get-multiples
  "Returns all multiples of n which are less than max."
  [n max]
  (range n max n))

(defn sum-of-multiples
  [nums max]
  (->> nums
       (map #(get-multiples % max))
       (reduce into #{})
       (apply +)))
