(ns triangle)

(defn is-valid?
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
  "Returns true if a triangle with sides n1 n2 n3 is equilateral,
  false otherwise."
  [n1 n2 n3]
  (and (is-valid? n1 n2 n3) (= n1 n2 n3)))

(defn isosceles?
  "Returns true if a triangle with sides n1 n2 n3 is isosceles,
  false otherwise."
  [n1 n2 n3]
  (and (is-valid? n1 n2 n3)
       (or (= n1 n2)
           (= n1 n3)
           (= n2 n3))))

(defn scalene?
  "Returns true if a triangle with sides n1 n2 n3 is scalene,
  false otherwise."
  [n1 n2 n3]
  (and (is-valid? n1 n2 n3)
       (not= n1 n2)
       (not= n1 n3)
       (not= n2 n3)))
