(ns raindrops)

(def factors [3 5 7])
(def sounds ["Pling" "Plang" "Plong"])
(def factor->sound (zipmap factors sounds))

(defn number->sounds
  "Converts a number to a string of its raindrop sounds.
  Returns an empty string if 3, 5, 7 are not factors of
  the number."
  [num]
  (let [factor-of-n? #(zero? (mod num %))]
    (->> factors
         (filter factor-of-n?)
         (map factor->sound)
         (apply str))))

(defn convert
  "Converts a number to its corresponding string of raindrop sounds."
  [num]
  (let [number-sounds (number->sounds num)]
    (if (= "" number-sounds)
      (str num)
      number-sounds)))
