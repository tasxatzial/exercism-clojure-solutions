(ns isbn-verifier)

(defn get-char-val
  "Returns the numeric value of a char."
  [c]
  (if (= c \X)
    10
    (- (int c) 48)))

(defn valid-isbn-pattern?
  "Returns true if the given string is a valid ISBN pattern,
  else false."
  [s]
  (->> s
       (re-matches #"\d{9}(X|\d)|\d-\d{3}-\d{5}-(X|\d)")
       boolean))

(defn isbn-val
  "Computes the value of a string according to the ISBN
  formula. The string must be a valid ISBN pattern."
  [s]
  (->> s
       (remove #(= % \-))
       (map get-char-val)
       (map * (range 10 0 -1))
       (apply +)))

(defn valid-isbn-val?
  "Returns true if the value of the string according to
  the ISBN formula is valid, else false. The string must be
  a valid ISBN pattern."
  [s]
  (-> s
      isbn-val
      (mod 11)
      zero?))

(defn isbn?
  "Returns true if the given string represents a valid ISBN."
  [s]
  (and (valid-isbn-pattern? s) (valid-isbn-val? s)))
