(ns isbn-verifier)

(defn digit?
  "Returns true if character c represents a digit,
  false otherwise."
  [c]
  (<= 48 (int c) 57))

(defn valid-left-part?
  "Checks if the left part of the isbn is valid. It must have
  9 digit characters."
  [isbn-left]
  (and (= 9 (count isbn-left))
       (every? digit? isbn-left)))

(defn left-part-value
  "Computes the numerical value of the left part of the
  isbn (9 chars)."
  [left-part]
  (let [digits (map #(Character/digit ^char % 10) left-part)]
    (apply + (map * digits (range 10 1 -1)))))

(defn valid-right-char?
  "Checks if the rightmost char of the isbn is valid."
  [c]
  (or (= \X c) (digit? c)))

(defn right-char-value
  "Returns the numerical value of the  rightmost char of
  the isbn."
  [c]
  (if (= \X c)
    10
    (Character/digit ^char c 10)))

(defn isbn?
  "Checks whether an isbn is valid."
  [isbn]
  (let [no-dashes (remove #{\-} isbn)
        left-part (butlast no-dashes)
        right-char (last no-dashes)]
    (if (and (valid-left-part? left-part)
             (valid-right-char? right-char))
      (let [lval (left-part-value left-part)
            rval (right-char-value right-char)]
        (= 0 (mod (+ lval rval) 11)))
      false)))
