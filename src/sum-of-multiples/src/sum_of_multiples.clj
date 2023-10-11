(ns sum-of-multiples)

(defn get-multiples
  "Returns all multiples of n which are less than max."
  [n max]
  (range n max n))

(defn remove-multiples
  "Sorts the given numbers and removes the numbers which are
  multiples of some other number."
  [nums]
  (reduce (fn [result n]
            (if (some #(zero? (mod n %)) result)
              result
              (conj result n)))
          []
          (sort nums)))

(defn sum-of-multiples
  [nums max]
  (->> nums
       remove-multiples
       (map #(get-multiples % max))
       (reduce into #{})
       (reduce +)))
