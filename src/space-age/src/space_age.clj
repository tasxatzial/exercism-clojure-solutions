(ns space-age)

(def earth-period 31557600)

;; Planet periods relative to earth period
(def planet-relative-periods
  {:earth 1
   :mercury 0.2408467
   :venus 0.61519726
   :mars 1.8808158
   :jupiter 11.862615
   :saturn 29.447498
   :uranus 84.016846
   :neptune 164.79132})

(defn get-planet-period
  [planet-relative-period]
  (* earth-period planet-relative-period))

(defn compute-planet-periods
  []
  (let [planet-names (keys planet-relative-periods)
        planet-relative-periods (vals planet-relative-periods)
        new-planet-periods (map get-planet-period planet-relative-periods)]
    (zipmap planet-names new-planet-periods)))

(def memoized_compute-planet-periods (memoize compute-planet-periods))

(defn on-mercury
  [seconds]
  (/ seconds (:mercury (memoized_compute-planet-periods))))

(defn on-venus
  [seconds]
  (/ seconds (:venus (memoized_compute-planet-periods))))

(defn on-earth
  [seconds]
  (/ seconds (:earth (memoized_compute-planet-periods))))

(defn on-mars
  [seconds]
  (/ seconds (:mars (memoized_compute-planet-periods))))

(defn on-jupiter
  [seconds]
  (/ seconds (:jupiter (memoized_compute-planet-periods))))

(defn on-saturn
  [seconds]
  (/ seconds (:saturn (memoized_compute-planet-periods))))

(defn on-neptune
  [seconds]
  (/ seconds (:neptune (memoized_compute-planet-periods))))

(defn on-uranus
  [seconds]
  (/ seconds (:uranus (memoized_compute-planet-periods))))
