(ns reverse-string)

;; solution 1
(defn reverse-string
  [s]
  (apply str (into () s)))

;; solution 2: built-in!
(defn reverse-string2
  [s]
  (clojure.string/reverse s))
