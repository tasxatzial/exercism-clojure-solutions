(ns pangram
  (:require [clojure.string :as str]))

;; solution 2

(def alphabet "abcdefhijklmnopqrstuvwxyz")

(defn pangram?
  "Returns true if the given string is a pangram;
  otherwise, it returns false."
  [s]
  (-> (str/lower-case s)
      set
      (every? alphabet)))
