(ns gigasecond)

(defn extract-year-month-day
  "Returns a vector that contains the year, month, and day
  of a LocalDateTime object."
  [localDateTime]
  (map #(% localDateTime) [#(.getYear %) #(.getMonthValue %) #(.getDayOfMonth %)]))

(defn from
  "Determines the date one gigasecond after the given date."
  [^long year ^long month ^long day]
  (-> (java.time.LocalDate/of year month day)
      (.atStartOfDay)
      (.plusNanos (Math/round 1e18))
      extract-year-month-day))
