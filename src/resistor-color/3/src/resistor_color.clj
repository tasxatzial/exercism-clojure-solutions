(ns resistor-color)

(def colors
  ["black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white"])

;; Java interop solution --------------------
(defn color-code
  "Returns the numerical value associated with the given color"
  [color]
  (.indexOf colors color))

;; Clojure solution -------------------------
(def color->color-code (zipmap colors (range)))

(defn color-code
  "Returns the numerical value associated with the given color"
  [color]
  (color->color-code color))
