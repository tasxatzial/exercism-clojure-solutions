(ns grains)

(defn transform->1
  "Returns a vector that indicates a sequence of operations
  that transform number n to 1. A 1 indicates decrement by 1
  by 2, a 2 indicates division by 2.

  n must be integer >=0 otherwise it returns nil.
  n = 0 is a special case and it returns [].

  E.g.
  for n = 19 the sequence of operations is:
  19-1, 18/2, 9-1, 8/2 4/2 2/2
  so the result would be [1 2 1 2 2 2]"
  [n]
  (if (not (int? n))
    nil
    (if (= 0 n)
      []
      (loop [result []
             num n]
        (cond
          (= 1 num) result
          (even? num) (recur (conj result 2)
                             (/ num 2))
          :else (recur (conj result 1)
                       (dec num)))))))

(defn square
  "Returns 2^(n-1).

  n must be integer >=1 otherwise it returns nil.

  Internally, the function uses transform->1 to generate a sequence
  of transforms in order to compute 2^(n-1) in O(logn) time. E.g
  for n = 20 the sequence of transforms of n = 19 is generated:
  [1 2 1 2 2 2]. This vector is traversed in reverse order and the
  following operations take place:
  2^2, 4^2, 16^2, 256 * 2, 512^2, 262144 * 2.
  Thus 2^19 = 524288"
  [n]
  (if (not (int? n))
    nil
    (if (= n 1)
      1
      (let [transforms (rseq (transform->1 (dec n)))]
        (reduce (fn [result x]
                  (if (= x 1)
                    (*' result 2)
                    (*' result result)))
                2
                transforms)))))

(defn total
  "Returns the total number of grains."
  []
  (dec (square 65)))
