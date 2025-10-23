(ns allergies)

(def allergens [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats])
(def allergen->index (zipmap allergens (range (count allergens))))

(defn allergic-to?
  "Returns true if the score indicates an allergy to the allergen;
  otherwise, it returns false."
  [score allergen]
  (bit-test score (allergen->index allergen)))

(defn allergies
  "Returns all allergens associated with the score."
  [score]
  (filter #(allergic-to? score %) allergens))
