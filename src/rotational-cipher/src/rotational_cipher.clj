(ns rotational-cipher)

(defn rotate-int
  "Rotates num by n. Num should be in [low, low + 26]"
  [low num n]
  (+ low (mod (+ n (- num low)) 26)))

(defn rotate-char
  "Rotates the char c by n."
  [n c]
  (let [ascii-val (int c)]
    (if (Character/isLetter ascii-val)
      (if (Character/isLowerCase ascii-val)
        (char (rotate-int 97 ascii-val n))
        (char (rotate-int 65 ascii-val n)))
      c)))

(defn rotate
  [s n]
  (->> s
       (map #(rotate-char n %))
       (apply str)))
