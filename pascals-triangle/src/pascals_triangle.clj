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

(defn pascals-rows
  "Returns a lazy sequence of the rows of pascal's triangle."
  ([]
   (pascals-rows 1))
  ([n]
   (lazy-seq
     (cons (row n) (pascals-rows (inc n))))))

(def triangle (pascals-rows))
