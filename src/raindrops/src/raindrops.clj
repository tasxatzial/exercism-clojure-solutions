(ns raindrops)

(def numbers [3 5 7])
(def pl_ng ["Pling" "Plang" "Plong"])
(def factor->str (zipmap numbers pl_ng))

(defn num->string
  "Converts a number to a string. Returns an empty string if
  3, 5, 7 are not factors of the number."
  [n]
  (->> numbers
       (filter #(zero? (mod n %)))
       (map factor->str)
       (apply str)))

(defn convert
  [n]
  (let [s (num->string n)]
    (if (= "" s)
      (str n)
      s)))
