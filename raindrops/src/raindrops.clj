(ns raindrops)

(def numbers [3 5 7])
(def pl_ng ["Pling" "Plang" "Plong"])
(def factors (zipmap numbers pl_ng))

(defn num->string
  "Converts a number to a string based on its factors.
  Returns an empty string is none of the 3, 5, 7 are
  factors of the number."
  [n]
  (reduce (fn [result x]
            (if (zero? (mod n x))
              (str result (factors x))
              result))
          ""
          numbers))

(defn convert
  "Converts a number to a string based on its factors."
  [n]
  (let [s (num->string n)]
    (if (= "" s)
      (str n)
      s)))
