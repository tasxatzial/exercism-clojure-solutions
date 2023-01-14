(ns rotational-cipher)

(defn get-char-type
  "Returns:
  1) :uppercase if int-char represents an uppercase letter
  2) :lowercase if int-char represents a lowercase letter
  3) :other in all other cases."
  [int-char]
  (cond
    (<= 97 int-char 122) :uppercase
    (<= 65 int-char 90) :lowercase
    :else :other))

(defn rotate-int
  "Rotates num by the given amount. Num should satisfy
  lowest-int <= num <= lowest-int + cycle-length."
  [lowest-int cycle-length num amount]
  (+ lowest-int (mod (+ amount (- num lowest-int)) cycle-length)))

;; rotate functions based on char type
(def rotate-fn
  {:lowercase (partial rotate-int 65 26)
   :uppercase (partial rotate-int 97 26)})

(defn rotate-char
  "Rotates the char c by n."
  [n c]
  (let [int-char (int c)
        char-type (get-char-type int-char)]
    (if-let [rotate-fn (rotate-fn char-type)]
      (char (rotate-fn int-char n))
      c)))

(defn rotate
  [s n]
  (let [rotate-char-by-n (partial rotate-char n)]
    (->> s
         (map rotate-char-by-n)
         (apply str))))
