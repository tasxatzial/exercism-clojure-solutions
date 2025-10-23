(ns pangram
  (:require [clojure.string :as str]))

;; solution 1

(defn pangram?
  "Returns true if the given string is a pangram;
  otherwise, it returns false."
  [s]
  (->> (str/lower-case s)
       (re-seq #"[a-z]")
       set
       count
       (= 26)))
