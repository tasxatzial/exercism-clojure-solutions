(ns series)

(defn slices
  "Return all the contiguous substrings of length n in
   a string in the order that they appear."
  [string length]
  (if (= 0 length)
    [""]
    (loop [result []
           digits (seq string)]
      (let [slice (take length digits)]
        (if (< (count slice) length)
          result
          (recur (conj result (apply str slice))
                 (rest digits)))))))
