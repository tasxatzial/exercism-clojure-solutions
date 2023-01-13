(ns triangle)

(defn valid-triangle?
  "Returns true if a triangle with sides n1 n2 n3 is valid,
  false otherwise."
  [n1 n2 n3]
  (and (pos? n1)
       (pos? n2)
       (pos? n3)
       (> (+ n1 n2) n3)
       (> (+ n1 n3) n2)
       (> (+ n2 n3) n1)))

(defn equilateral?
  [n1 n2 n3]
  (and (valid-triangle? n1 n2 n3)
       (= n1 n2 n3)))

(defn isosceles?
  [n1 n2 n3]
  (and (valid-triangle? n1 n2 n3)
       (or (= n1 n2)
           (= n1 n3)
           (= n2 n3))))

(defn scalene?
  [n1 n2 n3]
  (and (valid-triangle? n1 n2 n3)
       (not= n1 n2)
       (not= n1 n3)
       (not= n2 n3)))
