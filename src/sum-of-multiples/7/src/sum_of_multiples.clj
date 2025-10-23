(ns sum-of-multiples)

(defn get-multiples
  "Returns all multiples of num which are less than max."
  [num max]
  (range num max num))

(defn remove-multiples
  "Sorts the given numbers and removes the numbers which are
  multiples of some other number."
  [numbers]
  (reduce (fn [result n]
              (if (some #(zero? (mod n %)) result)
                result
                (conj result n)))
            []
            (sort numbers)))

(defn sum-of-multiples
  "Calculates the sum of multiples of the given numbers
  that are less than the limit."
  [numbers max]
  (->> numbers
       remove-multiples
       (map #(get-multiples % max))
       (reduce into #{})
       (reduce +)))