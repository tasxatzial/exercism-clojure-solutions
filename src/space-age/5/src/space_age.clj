(ns space-age)

(def earth-period-in-secs 31557600)

(def planet->earth-relative-period
  {:earth 1
   :mercury 0.2408467
   :venus 0.61519726
   :mars 1.8808158
   :jupiter 11.862615
   :saturn 29.447498
   :uranus 84.016846
   :neptune 164.79132})

(def planet->period-in-secs
  (let [planets (keys planet->earth-relative-period)]
    (->> planets
         (map #(* earth-period-in-secs (planet->earth-relative-period %)))
         (zipmap planets))))

(defn compute-space-age
  [planet seconds]
  (/ seconds (planet->period-in-secs planet)))

(defn on-mercury
  [seconds]
  (compute-space-age :mercury seconds))

(defn on-venus
  [seconds]
  (compute-space-age :venus seconds))

(defn on-earth
  [seconds]
  (compute-space-age :earth seconds))

(defn on-mars
  [seconds]
  (compute-space-age :mars seconds))

(defn on-jupiter
  [seconds]
  (compute-space-age :jupiter seconds))

(defn on-saturn
  [seconds]
  (compute-space-age :saturn seconds))

(defn on-neptune
  [seconds]
  (compute-space-age :neptune seconds))

(defn on-uranus
  [seconds]
  (compute-space-age :uranus seconds))