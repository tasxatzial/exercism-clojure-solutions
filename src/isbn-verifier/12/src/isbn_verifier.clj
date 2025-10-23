(ns isbn-verifier)

(defn- get-value
  [ch]
  (if (= ch \X)
    10
    (Character/digit ^char ch 10)))

(defn- valid-isbn-value?
  [isbn]
  (->> (remove #{\-} isbn)
       (map get-value)
       (map * (range 10 0 -1))
       (reduce +)
       (#(mod % 11))
       zero?))

(defn valid-isbn-pattern?
  [isbn]
  (boolean (re-matches #"\d{9}(X|\d)|\d-\d{3}-\d{5}-(X|\d)" isbn)))

(defn isbn?
  "Returns true if the given isbn is valid;
  otherwise, it returns false."
  [isbn]
  (and (valid-isbn-pattern? isbn) (valid-isbn-value? isbn)))
