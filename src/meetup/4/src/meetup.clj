(ns meetup)

(def week-day-count 7)

(def first-day 1)

;; thirteenth is the first teenth day
(def first-teenth-day 13)

(def day-name->value
  {:monday 1
   :tuesday 2
   :wednesday 3
   :thursday 4
   :friday 5
   :saturday 6
   :sunday 7})

(def position->value
  {:first 0
   :second 1
   :third 2
   :fourth 3})

(defn get-day-value
  "Returns the value of the given day as a number from 1 (monday) to 7 (sunday)."
  [^long year ^long month ^long day]
  (-> (java.time.LocalDate/of year month day)
      .getDayOfWeek
      .getValue))

(defn get-month-days-count
  "Returns the number of the days in the given month."
  [^long month ^long year]
  (.lengthOfMonth (java.time.YearMonth/of year month)))

(defn get-positioned-day
  "Returns the day (1-31) given the day name and its position within a month.
  Valid positions are :first :second :third :fourth"
  [month year day-name position]
  (let [day-value (day-name->value day-name)
        month-first-day-value (get-day-value year month first-day)
        position-value (position->value position)]
    (->> week-day-count
         (mod (- day-value month-first-day-value))
         (+ 1 (* week-day-count position-value)))))

(defn get-last-day
  "Returns the last day (1-31) of the given month."
  [month year day-name]
  (let [day-value (day-name->value day-name)
        month-days-count (get-month-days-count month year)
        month-last-day-value (get-day-value year month month-days-count)]
    (->> week-day-count
         (mod (- month-last-day-value day-value))
         (- month-days-count))))

(defn get-teenth-day
  "Returns the teenth day (13-19) of the given month."
  [month year day-name]
  (let [day-value (day-name->value day-name)
        first-teenth-day-value (get-day-value year month first-teenth-day)]
    (->> week-day-count
         (mod (- day-value first-teenth-day-value))
         (+ first-teenth-day))))

(defn get-day
  "Returns the day (1-31) given the day name and its position within a month.
  Valid positions are :first :second :third :fourth :teenth :last"
  [month year day-name position]
  (case position
    :teenth (get-teenth-day month year day-name)
    :last (get-last-day month year day-name)
    (get-positioned-day month year day-name position)))

(defn meetup
  [month year day-name position]
  (let [day (get-day month year day-name position)]
    [year month day]))
