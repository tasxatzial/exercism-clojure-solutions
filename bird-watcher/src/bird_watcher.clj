(ns bird-watcher)

(def last-week
  [0 2 5 3 7 8 4])

(defn today
  [birds]
  (last birds))

(defn inc-bird
  [birds]
  (assoc birds (dec (count birds)) (inc (today birds))))

(defn day-without-birds?
  [birds]
  (or (some zero? birds) false))

(defn n-days-count
  [birds n]
  (apply + (take n birds)))

(defn busy-days
  [birds]
  (->> birds
       (filter #(>= % 5))
       count))

(defn extract-indexes
  [pred]
  (partial keep-indexed #(if (pred %1) %2)))

(def extract-odd-indexes (extract-indexes odd?))
(def extract-even-indexes (extract-indexes even?))

(defn odd-week?
  [birds]
  (let [even-days (extract-even-indexes birds)
        odd-days (extract-odd-indexes birds)]
    (and (every? #{1} even-days)
         (every? #{0} odd-days))))
