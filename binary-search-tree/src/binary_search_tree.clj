(ns binary-search-tree)

(defn value
  "Returns the value of a node."
  [node]
  (first node))

(defn singleton
  "Creates a node from the given val."
  [val]
  [val nil nil])

(defn left
  "Returns the left child of node."
  [node]
  (second node))

(defn right
  "Returns the right child of node."
  [node]
  (node 2))

(defn insert
  "Inserts a value into the given tree."
  [val node]
  (if (nil? node)
    (singleton val)
    (if (<= val (value node))
      (assoc node 1 (insert val (left node)))
      (assoc node 2 (insert val (right node))))))

(defn to-list
  "Traverses a tree and returns a list of values
  (increasing order)"
  [node]
  (when node
    (concat (to-list (left node))
            [(value node)]
            (to-list (right node)))))

(defn from-list
  "Creates a tree from the given sequence of values."
  [coll]
  (reduce #(insert %2 %1) nil coll))
