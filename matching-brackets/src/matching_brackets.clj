(ns matching-brackets)

;; using a vector as stack

(def brackets #{\{ \} \( \) \[ \]})
(def matching-bracket {\{ \} \( \) \[ \]})

(defn valid?
  "Returns true if the given string has properly
  matched brackets, false otherwise."
  [s]
  (loop [stack []
         [bracket & rest-brackets] (filter brackets s)]
    (if bracket
      (if (matching-bracket bracket)
        (recur (conj stack bracket) rest-brackets)
        (if (= bracket (matching-bracket (peek stack)))
          (recur (pop stack) rest-brackets)
          false))
      (empty? stack))))
