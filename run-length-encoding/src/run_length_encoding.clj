(ns run-length-encoding)

(defn encode-rule
  "Encodes a sequence consisting of the same characters.
  E.g. (A A A) -> 3A"
  [coll]
  (let [cnt (count coll)]
    (if (> cnt 1)
      (str cnt (first coll))
      (str (first coll)))))

(defn decode-rule
  "Decodes an encoded string consisting of only one character.
  E.g. 3A -> AAA"
  [s]
  (let [ch (re-find #"\D" s)]
    (if-let [num (re-find #"\d+" s)]
      (->> ch
           (repeat (Integer/parseInt num))
           (apply str))
      ch)))

(def encode-transform
  "Encoding transducer. Transforms a string to its encoded chunks."
  (comp
    (partition-by identity)
    (map encode-rule)))

(defn run-length-encode
  [s]
  (->> s
       (transduce encode-transform conj)
       (apply str)))

(defn run-length-decode
  [s]
  (->> s
       (re-seq #"\d*.")
       (map decode-rule)
       (apply str)))
