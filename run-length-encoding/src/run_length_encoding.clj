(ns run-length-encoding)

(defn encode
  "Takes a seq of characters and returns a compressed string.
  Assumes that all characters are the same."
  [coll]
  (let [coll-count (count coll)]
    (if (> coll-count 1)
      (str coll-count (first coll))
      (str (first coll)))))

(defn decode
  "Takes a string that represents a compressed character sequence
  and decompresses it into a seq. For example: 3B is converted into
  a seq of three B strings."
  [str]
  (let [str-seq (re-seq #"\d+|\D" str)]
    (if-let [char (second str-seq)]
      (->> char
           repeat
           (take (Integer/parseInt (first str-seq))))
      str-seq)))

(defn run-length-encode
  [text]
  (->> text
       (partition-by identity)
       (map encode)
       (apply str)))

(defn run-length-decode
  [text]
  (->> text
       (re-seq #"\d*.")
       (map decode)
       (apply concat)
       (apply str)))
