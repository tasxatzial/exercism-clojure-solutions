(ns rotational-cipher)

(defn get-char-type
  "Returns:
  1) :uppercase if ascii val corresponds to an uppercase letter
  2) :lowercase if ascii val corresponds to a lowercase letter
  3) :other in all other cases."
  [ascii-val]
  (cond
    (<= 97 ascii-val 122) :uppercase
    (<= 65 ascii-val 90) :lowercase
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
  (let [ascii-val (int c)
        char-type (get-char-type ascii-val)]
    (if-let [rotate-fn (rotate-fn char-type)]
      (char (rotate-fn ascii-val n))
      c)))

(defn rotate
  [s n]
  (let [rotate-char-by-n (partial rotate-char n)]
    (->> s
         (map rotate-char-by-n)
         (apply str))))
