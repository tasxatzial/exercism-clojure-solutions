(ns matching-brackets)

;; using a vector as stack

(def brackets #{\{ \} \( \) \[ \]})
(def close-bracket {\{ \}
                    \( \)
                    \[ \]})

(defn open-bracket?
  "Returns true if c is an opening bracket."
  [c]
  (contains? close-bracket c))

(defn valid?
  "Returns true if the given string has properly
  matched brackets, false otherwise."
  [s]
  (loop [stack []
         [bracket & rest-brackets] (filter brackets s)]
    (if bracket
      (if (open-bracket? bracket)
        (recur (conj stack bracket) rest-brackets)
        (let [prev-bracket (peek stack)]
          (if (= bracket (close-bracket prev-bracket))
            (recur (pop stack) rest-brackets)
            false)))
      (empty? stack))))
