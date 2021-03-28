(ns clock)

(defn clock->string
  "Adds a zero in front of a 1-digit number"
  [[hour minutes]]
  (letfn [(add-zero [num]
            (if (< num 10)
              (str "0" num)
              (str num)))]
    (str (add-zero hour) ":" (add-zero minutes)))
)

(defn clock
  "Converts hours/minutes to a 24h/60m clock"
  [hours minutes]
  (let [minutes_q (if (< minutes 0)
                       (dec (quot minutes 60))
                       (quot minutes 60))]
    [(mod (+ minutes_q hours) 24) (mod minutes 60)])
)

(defn add-time
  "Adds minutes to a clock"
  [clock_ minutes]
  (clock (clock_ 0) (+ (clock_ 1) minutes))
)
