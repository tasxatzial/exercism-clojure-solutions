(ns matching-brackets)

(def l-bracket->r-bracket {\{ \} \( \) \[ \]})

(def brackets #{\{ \} \( \) \[ \]})

(defn l-bracket?
  [c]
  (contains? l-bracket->r-bracket c))

(defn valid?
  "Returns true if the given string has properly matched brackets;
  otherwise, it returns false."
  [s]
  (loop [stack []
         brackets (filter brackets s)]
    (if-let [bracket (first brackets)]
      (if (l-bracket? bracket)
        (recur (conj stack bracket) (rest brackets))
        (if (= bracket (l-bracket->r-bracket (peek stack)))
          (recur (pop stack) (rest brackets))
          false))
      (empty? stack))))
