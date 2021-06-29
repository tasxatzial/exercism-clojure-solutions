(ns matching-brackets)

(def brackets #{\{ \} \( \) \[ \]})
(def matches {\{ \} \( \) \[ \]})

(defn valid?
  "Returns true if the given string has properly
  matched brackets, false otherwise."
  [s]
  (loop [stack []
         s (filter brackets s)]
    (if (seq s)
      (let [bracket (first s)]
        (if (contains? matches bracket)
          (recur (conj stack bracket) (rest s))
          (if (= bracket (matches (peek stack)))
            (recur (pop stack) (rest s))
            false)))
      (empty? stack))))
