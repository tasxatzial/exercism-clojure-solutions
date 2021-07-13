(ns grade-school)

(defn grade
    "Returns a vector of school names in the given grade."
    [school grade]
    (get school grade []))

(defn add
    "Associates the given grade to a name."
    [school name grade]
    (let [grade-names (get school grade)]
        (if grade-names
            (update school grade conj name)
            (assoc school grade [name]))))

(defn sorted
    "Returns a sorted map of the school grades. Names in
    each grade are also sorted."
    [school]
    (reduce (fn [result [grade grade-names]]
                (conj result [grade (sort grade-names)]))
            (sorted-map) school))
