(ns meetup)

;; solution 1: functional approach

(def first-day 1)

(def
  ;; thirteenth is the first teenth day
  first-teenth-day 13)

(def day-name->value
  {:monday 1
   :tuesday 2
   :wednesday 3
   :thursday 4
   :friday 5
   :saturday 6
   :sunday 7})

(def day-values
  ;; A sequence composed by the day values in a 6-week period.
  (apply concat (repeat 6 [1 2 3 4 5 6 7])))

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

(def position->fn_day-select
  ;; Returns the appropriate function given a keyword that describes
  ;; the position of a day name. The function can be used to select the
  ;; correct day given a list of days that correspond to the day name.
  {:first first
   :second second
   :third #(nth % 2)
   :fourth #(nth % 3)
   :last last
   :teenth (fn [x] (some #(and (>= % first-teenth-day) %) x))})

(defn get-day
  "Returns the day (1-31) given the day name and its position within a month.
  Valid positions are :first :second :third :fourth :teenth :last"
  [month year day-name position]
  (let [day-value (day-name->value day-name)
        month-first-day-value (get-day-value year month first-day)
        month-day-count (get-month-days-count month year)
        month-day-values (take month-day-count (drop (dec month-first-day-value) day-values))]
    (->> month-day-values
         (keep-indexed #(if (= day-value %2) (inc %1)))
         ((position->fn_day-select position)))))

(defn meetup
  [month year day-name position]
  (let [day (get-day month year day-name position)]
    [year month day]))
