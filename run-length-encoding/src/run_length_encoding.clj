(ns run-length-encoding)

(defn encode
  "Takes a seq or string of the same characters and returns a
  compressed string."
  [coll]
  (let [coll-count (count coll)]
    (if (> coll-count 1)
      (str coll-count (first coll))
      (str (first coll)))))

(defn decode
  "Takes a string that represents a compressed character sequence
  and decompresses it."
  [s]
  (let [string-char (re-find #"\D" s)]
    (if-let [string-num (re-find #"\d+" s)]
      (->> string-char
           (repeat (Integer/parseInt string-num))
           (apply str))
      string-char)))

(defn run-length-encode
  [s]
  (->> s
       (partition-by identity)
       (map encode)
       (apply str)))

(defn run-length-decode
  [s]
  (->> s
       (re-seq #"\d*.")
       (map decode)
       (apply str)))
