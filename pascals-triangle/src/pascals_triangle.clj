(ns pascals-triangle)

(defn next-row
  "Given a row, it returns the next row of pascal's triangle."
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

(defn all-rows
  "Returns a lazy seq of the rows of the pascal triangle,
  starting from the row that has n items."
  ([n]
   (cons (row n)
         (lazy-seq (all-rows (inc n))))))

(def triangle (all-rows 1))
