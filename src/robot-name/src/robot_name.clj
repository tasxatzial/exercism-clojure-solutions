(ns robot-name)

;; This solution assumes that there are no parallel operations

(def names (atom #{}))

(defn gen-num []
  (rand-int 1000))

(defn gen-letter []
  (char (+ (int \A) (rand-int 26))))

(defn generate-name []
  (format "%s%s%03d" (gen-letter) (gen-letter) (gen-num)))

(defn reset-name
  [robot]
  (let [name (generate-name)]
    (if (@names name)
      (recur robot)
      (do
        (swap! names conj name)
        (reset! robot name)))))

(defn robot []
  (atom (reset-name (atom nil))))

(defn robot-name
  [robot]
  @robot)
