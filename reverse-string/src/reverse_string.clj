(ns reverse-string)

(defn reverse-string
  "Reverse a string."
  [s]
  (apply str (into () s)))
