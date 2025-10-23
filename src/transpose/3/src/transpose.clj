(ns transpose)

(defn pad-lines
  [lines]
  (if-let [rlines (seq (reverse lines))]
    (loop [reversed-lines (rest rlines)
           padded-lines [(first rlines)]]
      (if (seq reversed-lines)
        (let [diff (- (.length (peek padded-lines)) (.length (first reversed-lines)))
              new-padded-line (str (first reversed-lines) (apply str (repeat diff " ")))]
          (recur (rest reversed-lines) (conj padded-lines new-padded-line)))
        (rseq padded-lines)))
    []))

(defn transpose
  "Given a string, it returns the transposed version"
  [s]
  (loop [result []
         padded-lines (-> s clojure.string/split-lines pad-lines)]
    (if (seq (first padded-lines))
      (recur (conj result (map first padded-lines)) (map rest padded-lines))
      (clojure.string/join "\n" (map #(apply str %) result)))))
