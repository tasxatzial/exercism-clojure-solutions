(ns clock)

(defn prefix-zero
  "Prefixes n with 0 iff n < 10 and returns the corresponding string."
  [n]
  (if (< n 10)
    (str "0" n)
    (str n)))

(defn clock->string
  "Prefixes hour and/or minutes with a 0 (if they are < 10) and
  returns the string hour:minutes"
  [[hour minutes]]
  (str (prefix-zero hour) ":" (prefix-zero minutes)))

(defn min->hour
  "Calculates how many hours should be added or subtracted
  from an integer number of hours. E.g. 70 mins add 1 hour
  to 5 hours (6h+10min), -10 mins subtract -1 hour (4h+50min)"
  [^Integer minutes]
  (Math/floorDiv minutes 60))

(defn clock
  "Converts hours/minutes to 24h time."
  [hours minutes]
  (let [extra-hours (min->hour minutes)]
    [(mod (+ extra-hours hours) 24)
     (mod minutes 60)]))

(defn add-time
  "Adds minutes to a 24h time."
  [[hours minutes] extra-minutes]
  (clock hours (+ minutes extra-minutes)))
