(ns crypto-square)

;; solution 2

(defn normalize-plaintext
  "Keeps letters and numbers, converts to lowercase."
  [s]
  (->> s
       (re-seq #"[A-Za-z0-9]+")
       (apply str)
       clojure.string/lower-case))

(defn square-size
  "Returns a number c that can be used to partition the given
  string into r strings of maximum length c. The number c
  satisfies c >= r and c - r <= 1."
  [s]
  (-> s count Math/sqrt Math/ceil Math/round))

(defn plaintext-segments
  "Partitions the normalized string of s into a seq of strings.
  The number of strings and their size are determined by the
  square-size function."
  [s]
  (let [normalized (normalize-plaintext s)
        segment-length (square-size normalized)]
    (->> normalized
         (partition-all segment-length)
         (map #(apply str %)))))

(defn interleave-all
  "Returns a collection that consists of a seq of the first elements of
  each of the collections, then a seq of the second elements etc.
  Assumes that the first collection is the longest one. Non-existing
  elements are replaced with nil."
  [colls]
  (loop [result []
         colls colls]
    (if (seq (first colls))
      (recur (conj result (map first colls))
             (map rest colls))
      result)))

(defn ciphertext
  "Returns the encoded string of s."
  [s]
  (->> s
       normalize-plaintext
       plaintext-segments
       interleave-all
       (map #(apply str %))
       (apply str)))

(defn normalize-ciphertext
  "Returns a normalized version of the encoded string of s."
  [s]
  (->> s
       normalize-plaintext
       plaintext-segments
       interleave-all
       (map #(replace {nil \space} %))
       (map #(apply str %))
       (clojure.string/join " ")))
