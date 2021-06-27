(ns run-length-encoding)

(defn compress-coll
  "Takes a seq of characters and returns a compressed string.
  Assumes that all characters are the same. If seq has one item
  e.g. (x) it will return \"x\". If seq has more than one item
  e.g. (x x x) it will return \"3x\"."
  [coll]
  (if (> (count coll) 1)
    (str (count coll) (first coll))
    (str (first coll))))

(defn char->int
  "Converts a char to int. If the conversion cannot be made, it
  returns the same character."
  [c]
  (try
    (Integer/parseInt (str c))
    (catch NumberFormatException _ c)))

(defn run-length-encode
  "Encodes a string with run-length-encoding."
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (map compress-coll)
       (apply str)))

(defn decompress-str
  "Takes an integer n and a string s and returns a decompressed seq
  made of n copies of the first character followed by the remaining
  characters. E.g., if n = 2 and str = \"abc\" it returns (a a b c)."
  [n s]
  (let [decompressed-first (take n (repeat (first s)))]
    (concat decompressed-first (rest s))))

(defn split-cipher-text
  "Splits a compressed string into a seq of integers and substrings.
  E.g., \"a33bh3g\" is split into (\"a\" 33 \"bh\" 3 \"g\")."
  [cipher-text]
  (->> cipher-text
       (map char->int)
       (partition-by int?)
       (map #(apply str %))
       (map char->int)))

(defn run-length-decode
  "Decodes a run-length-encoded string."
  [cipher-text]
  (loop [result []
         [x & more] (split-cipher-text cipher-text)]
    (if x
      (if (int? x)
        (recur (into result (decompress-str x (first more)))
               (rest more))
        (recur (conj result x) more))
      (apply str result))))
