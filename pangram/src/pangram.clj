(ns pangram)

(def alphabet-letter-count 26)

(defn lower-case-letter?
  "Returns true if character c is a lower-case letter."
  [c]
  (<= 97 (int c) 122))

(defn count-different-letters
  "Returns the number of different letters contained in the given
  string (ignores case)."
  [s]
  (->> s
       clojure.string/lower-case
       (filter lower-case-letter?)
       set
       count))

(defn pangram?
  [s]
  (= alphabet-letter-count (count-different-letters s)))
