(ns clock)

(defn prefix-zero
  "Prefixes n with 0 if n < 10 and returns the corresponding string."
  [n]
  (if (< n 10)
    (str "0" n)
    (str n)))

(defn clock->string
  "Prefixes hour and/or minutes with a 0 (if they are < 10) and
  returns the string hour:minutes"
  [[hour minutes]]
  (str (prefix-zero hour) ":" (prefix-zero minutes)))

(defn clock
  "Converts hours/minutes to 24h time."
  [hours minutes]
  (let [extra-hours (Math/floorDiv ^Integer minutes 60)]
    [(mod (+ extra-hours hours) 24)
     (mod minutes 60)]))

(defn add-time
  "Adds minutes to a 24h time."
  [[hours minutes] extra-minutes]
  (clock hours (+ minutes extra-minutes)))
