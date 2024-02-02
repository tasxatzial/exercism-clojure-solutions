(ns raindrops)

(def factors [3 5 7])
(def sounds ["Pling" "Plang" "Plong"])
(def factor->sound (zipmap factors sounds))

(defn number->sounds
  "Converts a number to a string of its raindrop sounds.
  Returns an empty string if 3, 5, 7 are not factors of
  the number."
  [n]
  (let [factor-of-n? #(zero? (mod n %))]
    (->> factors
         (filter factor-of-n?)
         (map factor->sound)
         (apply str))))

(defn convert
  [n]
  (let [number-sounds (number->sounds n)]
    (if (= "" number-sounds)
      (str n)
      number-sounds)))
