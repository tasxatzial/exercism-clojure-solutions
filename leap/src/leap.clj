(ns leap)

(defn leap-year?
  "Returns true if the given year is a leap year, false otherwise."
  [year]
  (or (and (= 0 (mod year 4))
           (not= 0 (mod year 100)))
      (= 0 (mod year 400))))
