(ns isbn-verifier)

(defn get-char-val
  "Returns the numeric value of a char."
  [ch]
  (if (= ch \X)
    10
    (Character/digit ^char ch 10)))

(defn valid-isbn-pattern?
  "Returns true if the given string is a valid ISBN pattern, else false."
  [s]
  (boolean (re-matches #"\d{9}(X|\d)|\d-\d{3}-\d{5}-(X|\d)" s)))

(defn isbn-val
  "Computes the value of a string according to the ISBN formula.
  The string must be a valid ISBN pattern."
  [s]
  (->> (remove #{\-} s)
       (map get-char-val)
       (map * (range 10 0 -1))
       (reduce +)))

(defn isbn?
  "Returns true if the given string represents a valid ISBN."
  [s]
  (and (valid-isbn-pattern? s)
       (zero? (mod (isbn-val s) 11))))
