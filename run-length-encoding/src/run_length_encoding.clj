(ns run-length-encoding)

(defn encode-single
  "Takes a string that consists of the same char and
  returns the compressed equivalent."
  [coll]
  (let [coll-count (count coll)]
    (if (> coll-count 1)
      (str coll-count (first coll))
      (str (first coll)))))

(defn decode-single
  "Takes a string that represents the compressed form of a
  sequence that consists of the same char and returns the
  decompressed equivalent."
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
       (map encode-single)
       (apply str)))

(defn run-length-decode
  [s]
  (->> s
       (re-seq #"\d*.")
       (map decode-single)
       (apply str)))
