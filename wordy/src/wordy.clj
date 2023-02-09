(ns wordy)

(def string->op
  {"plus" +
   "minus" -
   "divided" /
   "multiplied" *})

(defn parse-token
  "Returns the symbol (number or function) that corresponds
  to the given string token."
  [s]
  (try
    (Integer/parseInt s)
    (catch Exception _
      (string->op s))))

(defn parse-string
  "Parses the given string into a seq of symbols
  (numbers or functions)."
  [s]
  (->> s
       (re-seq #"-?\w+")
       (drop 2)
       (remove #{"by"})
       (map parse-token)))

(defn evaluate
  "Evaluates the given sentence."
  [s]
  (let [expr (parse-string s)]
    (if (some nil? expr)
      (throw (IllegalArgumentException.))
      (loop [result (first expr)
             tokens (rest expr)]
        (if (seq tokens)
          (let [[f num & rest-tokens] tokens]
            (recur (f result num) rest-tokens))
          result)))))
