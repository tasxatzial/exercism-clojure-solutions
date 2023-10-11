(ns grade-school)

(defn grade
  "Get a list of all students enrolled in a grade."
  [school grade]
  (get school grade []))

(defn add
  "Add a student's name to the given grade."
  [school name grade]
  (update school grade (fnil conj []) name))

(defn sorted
  "Get a sorted map (by grade) of all students. Names in
  each grade are also sorted."
  [school]
  (reduce (fn [result [grade grade-names]]
            (conj result [grade (sort grade-names)]))
          (sorted-map)
          school))
