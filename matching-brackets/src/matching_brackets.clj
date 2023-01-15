(ns matching-brackets)

(def get-closing-bracket {\{ \},
                          \( \),
                          \[ \]})

(def brackets (-> #{}
                  (into (keys get-closing-bracket))
                  (into (vals get-closing-bracket))))

(defn opening-bracket?
  "Returns true if c is an opening bracket, else false."
  [c]
  (contains? get-closing-bracket c))

;; using a vector as stack
(defn valid?
  "Returns true if the given string has properly
  matched brackets, else false."
  [s]
  (loop [stack []
         [curr-bracket & rest-brackets] (filter brackets s)]
    (if curr-bracket
      (if (opening-bracket? curr-bracket)
        (recur (conj stack curr-bracket) rest-brackets)
        (let [stack-top-bracket (peek stack)]
          (if (= curr-bracket (get-closing-bracket stack-top-bracket))
            (recur (pop stack) rest-brackets)
            false)))
      (empty? stack))))
