(ns crypto-square)

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

(defn partition-into-square
  "Partitions the normalized string of s into a sequence of lists of
  n = (square-size (norm s)) characters each. Lists that have less
  than n items are padded with the given character."
  [s pad]
  (let [normalized (normalize-plaintext s)
        segment-length (square-size normalized)]
    (partition segment-length segment-length (repeat pad) normalized)))

(defn plaintext-segments
  "Partitions the normalized string of s into a sequence of strings.
  The number of strings and their size are determined by the
  square-size function."
  [s]
  (->> (partition-into-square s nil)
       (map #(apply str %))))

(defn encode
  "Encodes the given string s. If a non-nil pad character is supplied,
  it will be used as a placeholder in the normalized version."
  [s pad]
  (if (= s "")
    ""
    (->> (partition-into-square s pad)
         (apply map str)
         (clojure.string/join pad))))

(defn ciphertext
  "Returns the encoded string of s."
  [s]
  (encode s nil))

(defn normalize-ciphertext
  "Returns a normalized version of the encoded string of s."
  [s]
  (encode s \space))
