(ns allergies)

(def allergens
  {1 :eggs
   2 :peanuts
   4 :shellfish
   8 :strawberries
   16 :tomatoes
   32 :chocolate
   64 :pollen
   128 :cats})

(def max-score
  (apply max (keys allergens)))

(defn math-pow
  "Calculates x^n."
  [x n]
  (loop [result 1
         remaining n]
    (if (= 0 remaining)
      result
      (recur (* x result)
             (dec remaining)))))

(defn decimal->reverse-binary
  "Returns a seq representing number n in reverse binary form."
  [n]
  (loop [remainders []
         n0 n]
    (if (= 0 n0)
      remainders
      (recur (conj remainders (rem n0 2))
             (quot n0 2)))))

(defn allergen-scores
  "Returns all individual scores contained in a final score."
  [score]
  (let [reverse-binary (decimal->reverse-binary score)]
    (loop [digits reverse-binary
           power 0
           result []]
      (if (seq digits)
        (if (= 1 (first digits))
          (recur (rest digits)
                 (inc power)
                 (conj result (math-pow 2 power)))
          (recur (rest digits)
                 (inc power)
                 result))
        result))))

(defn ignore-allergens
  "Ignores all scores > max-score."
  [scores]
  (take-while #(<= % max-score) scores))

(defn allergies
  "Returns a seq of all allergens based on a final score."
  [score]
  (let [scores (-> score
                   allergen-scores
                   ignore-allergens)]
    (map allergens scores)))

(defn allergic-to?
  "Returns true if the allergen is contained in the
  allergens represented by the given score."
  [score allergen]
  (some #(= % allergen)
        (allergies score)))
