(ns grade-school)

(defn grade
    "Returns a vector of school names in the given grade."
    [school grade]
    (get school grade []))

(defn add
    "Adds a name to the given grade vector."
    [school name grade]
    (let [grade-names (get school grade)]
        (if grade-names
            (update school grade conj name)
            (assoc school grade (conj [] name)))))

(defn sorted
    "Returns a sorted map of the school grades. Names in
    each grade are also sorted."
    [school]
    (reduce (fn [result [grade grade-names]]
                (conj result [grade (sort grade-names)]))
            (sorted-map) school))
