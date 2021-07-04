(ns rotational-cipher)

(defn rotate-char
  "Shifts character c n times."
  [c n]
  (let [int-c (int c)]
    (char (cond
            (<= 65 int-c 90) (+ 65 (mod (+ n (- int-c 65)) 26))
            (<= 97 int-c 122) (+ 97 (mod (+ n (- int-c 97)) 26))
            :else c))))

(defn rotate
  "Shifts every letter character in s n times."
  [s n]
  (apply str (map #(rotate-char % n) s)))
