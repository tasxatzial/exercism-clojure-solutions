(ns atbash-cipher
  (:require [clojure.string :as str]))

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def alphabet-encode-map (zipmap alphabet (reverse alphabet)))
(def alphabet-decode-map (zipmap (reverse alphabet) alphabet))

(def encode-transform
  "Encoding transducer for lowercased strings. Encodes input into strings of fixed length (5)."
  (comp
    (filter #(or (Character/isDigit ^char %) (Character/isLetter ^char %)))
    (map #(get alphabet-encode-map % %))
    (partition-all 5)
    (map #(apply str %))))

(defn encode
  "Encodes text using the Atbash cipher."
  [plaintext]
  (->> plaintext
       str/lower-case
       (into [] encode-transform)
       (str/join " ")))

(defn remove-spaces
  [s]
  (str/replace s #" " ""))

(defn decode
  "Decodes text using the Atbash cipher."
  [ciphertext]
  (->> ciphertext
       remove-spaces
       (map #(or (alphabet-decode-map %) %))
       (apply str)))
