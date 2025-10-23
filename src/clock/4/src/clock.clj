(ns clock)

(defn prefix-zero
  "Prefixes the given integer with 0 if it is < 10.
  Returns a string."
  [n]
  (if (< n 10)
    (str "0" n)
    (str n)))

(defn clock->string
  "Converts a vector that represents a 24h clock to a string."
  [v]
  (let [[hours minutes] v]
    (str (prefix-zero hours) ":" (prefix-zero minutes))))

(defn clock
  "Converts the given hours and minutes to their equivalent 24h
  values. Returns a vector."
  [hours minutes]
  (let [extra-hours (Math/floorDiv ^Integer minutes 60)]
    [(mod (+ extra-hours hours) 24)
     (mod minutes 60)]))

(defn add-time
  "Adds the given minutes to a vector that represents a 24h clock."
  [v extra-minutes]
  (let [[hours minutes] v]
    (clock hours (+ minutes extra-minutes))))
