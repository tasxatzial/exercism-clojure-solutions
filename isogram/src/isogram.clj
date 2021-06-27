(ns isogram)

(defn collect-letters
  "Collects the letters of lowercase s in a vector."
  [s]
  (let [lowercased (clojure.string/lower-case s)]
    (reduce (fn [result c]
              (if (or (= \space c) (= \- c))
                result
                (conj result c)))
            []
            lowercased)))

(defn isogram?
  "Returns true if s is isogram, false otherwise."
  [s]
  (let [letters (collect-letters s)
        letters-set (set letters)]
    (= (count letters) (count letters-set))))
