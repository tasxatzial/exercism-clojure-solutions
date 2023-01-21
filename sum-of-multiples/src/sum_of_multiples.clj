(ns sum-of-multiples)

(defn get-multiples
  "Returns all multiples of n which are less than max."
  [n max]
  (range n max n))

(defn keep-coprimes
  "Returns a sorted vector of numbers that contains only those
  which are coprime."
  [nums]
  (let [sorted-nums (sort nums)]
    (reduce (fn [result n]
              (if (some #(zero? (mod n %)) result)
                result
                (conj result n)))
            []
            sorted-nums)))

(defn sum-of-multiples
  [nums max]
  (->> nums
       keep-coprimes
       (map #(get-multiples % max))
       (reduce into #{})
       (apply +)))
