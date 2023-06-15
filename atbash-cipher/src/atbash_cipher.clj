(ns atbash-cipher)

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def alphabet-encode-map (zipmap alphabet (reverse alphabet)))

(def encode-transform
  "Encoding transducer for lowercased strings. Encodes input into strings of fixed length (5)."
  (comp
    (filter #(or (Character/isDigit ^char %) (Character/isLetter ^char %)))
    (map #(get alphabet-encode-map % %))
    (partition-all 5)
    (map #(apply str %))))

(defn encode
  [s]
  (->> s
       clojure.string/lower-case
       (transduce encode-transform conj)
       (clojure.string/join " ")))
