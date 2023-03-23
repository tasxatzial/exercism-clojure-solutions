(ns bird-watcher)

(def odd-week
  [1 0 1 0 1 0 1])

(def last-week
  [0 2 5 3 7 8 4])

(defn today
  [birds]
  (peek birds))

(defn inc-bird
  [birds]
  (update birds (dec (count birds)) inc))

(defn day-without-birds?
  [birds]
  (true? (some zero? birds)))

(defn n-days-count
  [birds n]
  (reduce + (subvec birds 0 n)))

(defn busy-day?
  [bird-count]
  (>= bird-count 5))

(defn busy-days
  [birds]
  (->> birds
       (filter busy-day?)
       count))

(defn odd-week?
  [birds]
  (= birds odd-week))
