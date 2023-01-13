(ns atbash-cipher)

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def alphabet-encode-map (zipmap alphabet (reverse alphabet)))

(defn encode
  [s]
  (->> s
       clojure.string/lower-case
       (re-seq #"[A-Za-z0-9]")
       (apply str)
       (map #(get alphabet-encode-map % %))
       (partition-all 5)
       (map #(apply str %))
       (clojure.string/join " ")))
