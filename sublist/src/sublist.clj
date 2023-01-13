(ns sublist)

(defn sublist?
  "Returns true of list2 is a sublist of list1, false otherwise."
  [list1 list2]
  (let [partitioned-list1 (partition (count list2) 1 list1)]
    (or (some #(= list2 %) partitioned-list1)
        false)))

(defn classify
  [list1 list2]
  (if (and (empty? list1) (empty? list2))
    :equal
    (if (sublist? list2 list1)
      (if (= (count list1) (count list2))
        :equal
        :sublist)
      (if (sublist? list1 list2)
        :superlist
        :unequal))))
