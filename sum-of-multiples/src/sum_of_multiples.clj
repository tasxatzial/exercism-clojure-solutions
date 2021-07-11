(ns sum-of-multiples)

(defn multiples
  "Returns all multiples of n which are less than max."
  [n max]
  (range n max n))

(defn sum-of-multiples
  "Given a list of nums, find the sum of all the unique multiples of
  the numbers up to but not including the given max number."
  [nums max]
  (apply + (reduce #(into %1 (multiples %2 max))
                   #{} nums)))
