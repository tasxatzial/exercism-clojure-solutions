(ns atbash-cipher)

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def matches (zipmap alphabet (reverse alphabet)))

(defn encode
  "Encodes the given string using the atbash cipher."
  [s]
  (->> s
       clojure.string/lower-case
       (remove #{\. \space \,})
       (map #(get matches % %))
       (partition-all 5)
       (map #(apply str %))
       (clojure.string/join " ")))
