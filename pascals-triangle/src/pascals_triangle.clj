(ns pascals-triangle)

(defn next-row
  "Given a row, it returns the next row of pascal's triangle."
  [row]
  (let [zero-append (conj row 0)
        zero-prepend (into [0] row)]
    (vec (map +' zero-append zero-prepend))))

(defn row-N
  "Returns the Nth row of pascal's triangle."
  ([N]
   (case N
     1 [1]
     2 [1 1]
     (row-N (dec N)
            (next-row [1 1]))))
  ([N row]
   (if (= N 2)
     row
     (recur (dec N)
            (next-row row)))))

(defn all-rows
  "Returns a lazy seq of the rows of the pascal triangle."
  ([]
   (all-rows 1))
  ([n]
   (cons (row-N n)
         (lazy-seq (all-rows (inc n))))))

(def triangle (all-rows))
