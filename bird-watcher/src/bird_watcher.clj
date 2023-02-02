(ns bird-watcher)

(def odd-week
  [1 0 1 0 1 0 1])

(def last-week
  [0 2 5 3 7 8 4])

(defn today
  [birds]
  (last birds))

(defn inc-bird
  [birds]
  (let [todays-idx (dec (count birds))]
    (assoc birds todays-idx (inc (today birds)))))

(defn day-without-birds?
  [birds]
  (true? (some zero? birds)))

(defn n-days-count
  [birds n]
  (reduce + (take n birds)))

(defn busy-day?
  [bird-count]
  (>= bird-count 5))

(defn busy-days
  [birds]
  (->> birds
       (filterv busy-day?)
       count))

(defn odd-week?
  [birds]
  (= birds odd-week))
