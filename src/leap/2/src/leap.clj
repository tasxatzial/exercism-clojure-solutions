(ns leap)

(defn leap-year?
  "Returns true if the given year is a leap year, false otherwise."
  [year]
  (and (zero? (mod year 4))
       (or (pos? (mod year 100))
           (zero? (mod year 400)))))
