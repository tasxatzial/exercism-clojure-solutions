(ns pascals-triangle)

(defn next-row
  "Returns the next row of pascal's triangle."
  [row]
  (let [zero-append (conj row 0)]
    (mapv +' zero-append (rseq zero-append))))

(defn row
  "Returns the Nth row of pascal's triangle."
  [N]
  (loop [row [1]
         N N]
    (if (= N 1)
      row
      (recur (next-row row) (dec N)))))

(def triangle (iterate next-row [1]))
