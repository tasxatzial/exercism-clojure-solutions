(ns series)

(defn slices
  [string length]
  (if (zero? length)
    [""]
    (let [last-index (inc (- (count string) length))]
      (loop [result []
             index 0
             string string]
        (if (< index last-index)
          (let [slice (apply str (take length string))]
            (recur (conj result slice) (inc index) (rest string)))
          result)))))
