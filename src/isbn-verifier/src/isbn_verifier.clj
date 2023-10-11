(ns isbn-verifier)

(defn- get-value
  "Returns the numeric value of a char."
  [ch]
  (if (= ch \X)
    10
    (Character/digit ^char ch 10)))

(defn- isbn-value
 "Computes the value of a string according to the ISBN formula.
  The string must be a valid ISBN pattern."
  [s]
  (->> (remove #{\-} s)
       (map get-value)
       (map * (range 10 0 -1))
       (reduce +)))

(defn- valid-isbn-value?
  [val]
  (zero? (mod val 11)))

(defn valid-isbn-pattern?
  [s]
  (boolean (re-matches #"\d{9}(X|\d)|\d-\d{3}-\d{5}-(X|\d)" s)))

(defn isbn?
  [s]
  (and (valid-isbn-pattern? s) (valid-isbn-value? (isbn-value s))))
