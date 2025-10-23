(ns crypto-square)

;; solution 1

(defn normalize-plaintext
  "Normalizes a string: Keeps letters and numbers, converts
  to lowercase."
  [s]
  (->> s
       (re-seq #"[A-Za-z0-9]+")
       (apply str)
       clojure.string/lower-case))

(defn square-size
  "Returns a number that can be used to partition the given
  string into r strings of equal length c. The number satisfies
  c >= r and c - r <= 1."
  [s]
  (-> s count Math/sqrt Math/ceil Math/round))

(defn plaintext-segments
  "Partitions the given string according to the number specified
  by the square-size function."
  [s]
  (let [normalized (normalize-plaintext s)
        segment-length (square-size normalized)]
    (->> normalized
         (partition-all segment-length)
         (map #(apply str %)))))

(defn ciphertext
  "Returns the encoded string."
  [s]
  (let [normalized (normalize-plaintext s)
        segments (plaintext-segments normalized)]
    (loop [result []
           segments segments]
      (if (seq (first segments))
        (recur (into result (map first segments))
               (map rest segments))
        (apply str result)))))

(defn normalize-ciphertext
  "Returns a normalized version of the encoded string."
  [s]
  (let [normalized (normalize-plaintext s)
        segments (plaintext-segments normalized)]
    (loop [result []
           segments segments]
      (if (seq (first segments))
        (let [ciphertext-segment (apply str (replace {nil \space} (map first segments)))]
          (recur (conj result ciphertext-segment) (map rest segments)))
        (clojure.string/join " " result)))))
