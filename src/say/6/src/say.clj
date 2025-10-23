(ns say)

(def ^:private less-than-20
  ["" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "ten"
   "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen" "eighteen" "nineteen"])

(def ^:private multiple-of-10
  ["" "" "twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"])

;; extend by adding more weights and changing the limit as needed
(def ^:private weights
  ["" "thousand" "million" "billion"])

;; one trillion
(def limit
  "The minimum positive integer that is out of range."
  1000000000000)

(defn- get-groups
  "Assumes n > 0"
  [n]
  (loop [n n
         groups []]
    (if (zero? n)
      groups
      (recur (quot n 1000) (conj groups (mod n 1000))))))

(defn- say-less-than-1000
  "Assumes 0 < n < 1000"
  [n]
  (let [hundreds (quot n 100)
        hundreds-rest (mod n 100)
        tenths (quot hundreds-rest 10)
        ones (mod hundreds-rest 10)]
    (cond-> ""
            (pos? hundreds) (str (less-than-20 hundreds) " hundred ")
            true (as-> w
                       (if (< 0 hundreds-rest 20)
                         (str w (less-than-20 hundreds-rest) " ")
                         (cond-> w
                                 (and (pos? tenths)) (str (multiple-of-10 tenths))
                                 (and (pos? hundreds-rest) (zero? ones)) (str " ")
                                 (and (pos? tenths) (pos? ones)) (str "-")
                                 (pos? ones) (str (less-than-20 ones) " ")))))))

(defn- say-weight
  [i group]
  (if (pos? group)
    (str (weights i) " ")
    ""))

(defn valid?
  [n]
  (and (not (neg? n)) (integer? n) (< n limit)))

(defn number
  [n]
  (if (valid? n)
    (if (zero? n)
      "zero"
      (let [groups (get-groups n)]
        (->> (range (dec (count groups)) -1 -1)
             (map #(str (say-less-than-1000 (groups %)) (say-weight % (groups %))))
             (apply str)
             clojure.string/trimr)))
    (throw (IllegalArgumentException.))))
