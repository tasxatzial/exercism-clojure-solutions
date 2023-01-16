(ns allergies)

(def allergens [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats])
(def allergens-power (zipmap allergens (range (count allergens))))

(defn allergic-to?
  "Returns true if the score implies allergy to an allergen,
  else false."
  [score allergen]
  (->> allergen
       allergens-power
       (bit-test score)))

(defn allergies
  "Determines the allergies from the given score. Returns a vector."
  [score]
  (keep-indexed #(if (bit-test score %1) %2)
                allergens))
