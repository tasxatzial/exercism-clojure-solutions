(ns allergies)

(def allergens [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats])
(def allergen->power (zipmap allergens (range (count allergens))))

(defn allergic-to?
  "Returns true if the given score implies allergy to an allergen, else false."
  [score allergen]
  (bit-test score (allergen->power allergen)))

(defn allergies
  "Returns all the allergies associated with the given score."
  [score]
  (keep-indexed #(if (bit-test score %1) %2) allergens))
