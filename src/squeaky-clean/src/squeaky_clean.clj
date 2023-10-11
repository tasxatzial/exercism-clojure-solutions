(ns squeaky-clean
  (:require [clojure.string :as str]))

(defn clean
  [s]
  (-> s
      (str/replace #"\s" "_") ;task 1
      (str/replace #"\p{Cc}" "CTRL") ;task 2
      (str/replace #"-(\p{L})" #(str/upper-case (%1 1))) ;task 3
      (str/replace #"[^\p{L}_]|[α-ω]" ""))) ;task 4,5
