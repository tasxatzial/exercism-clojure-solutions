(ns sublist)

(defn check-sublist
  "Returns :sublist if list1 is a sublist of list2, else nil."
  [list1 list2]
  (let [l1-count (count list1)]
    (loop [l2 list2]
      (let [l2sub (take l1-count l2)]
        (when (= (count l2sub) l1-count)
          (if (= l2sub list1)
            :sublist
            (recur (rest l2))))))))

(defn check-equal
  "Returns :equal if list1 = list2, else :unequal."
  [list1 list2]
  (if (= list1 list2)
    :equal
    :unequal))

(defn classify
  "Returns
  :equal if list1 = list2
  :sublist if list1 is sublist of list2
  :superlist if list1 is superlist of list2
  else :unequal"
  [list1 list2]
  (if (= (count list1) (count list2))
    (check-equal list1 list2)
    (or (check-sublist list1 list2)
        (when (check-sublist list2 list1) :superlist)
        :unequal)))
