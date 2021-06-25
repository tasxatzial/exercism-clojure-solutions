(ns space-age)

;; Earth period in seconds
(def earth-period 31557600)

;; Planet periods relative to earth period
(def earth-relative-periods
  {:earth 1
   :mercury 0.2408467
   :venus 0.61519726
   :mars 1.8808158
   :jupiter 11.862615
   :saturn 29.447498
   :uranus 84.016846
   :neptune 164.79132})

;; Planet periods in seconds
(def planet-periods
  (reduce (fn [result [planet period]]
            (let [planet-period (* earth-period period)]
              (conj result [planet planet-period])))
          {}
          earth-relative-periods))

(defn on-mercury
  [seconds]
  (/ seconds (:mercury planet-periods)))

(defn on-venus
  [seconds]
  (/ seconds (:venus planet-periods)))

(defn on-earth
  [seconds]
  (/ seconds (:earth planet-periods)))

(defn on-mars
  [seconds]
  (/ seconds (:mars planet-periods)))

(defn on-jupiter
  [seconds]
  (/ seconds (:jupiter planet-periods)))

(defn on-saturn
  [seconds]
  (/ seconds (:saturn planet-periods)))

(defn on-neptune
  [seconds]
  (/ seconds (:neptune planet-periods)))

(defn on-uranus
  [seconds]
  (/ seconds (:uranus planet-periods)))
