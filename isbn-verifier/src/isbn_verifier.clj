(ns isbn-verifier)

(defn remove-dash
  "Removes the dashes from an isbn."
  [isbn]
  (filter #(not= \- %) isbn))

(defn valid-left-part?
  "Checks if the left part of the isbn is valid. It must have
  9 digit characters."
  [isbn-left]
  (if (not= (count isbn-left) 9)
    false
    (loop [[digit & more] isbn-left]
      (if (boolean digit)
        (if (<= 48 (int digit) 57)
          (recur more)
          false)
        true))))

(defn left-part->nums
  "Converts the left part of the isbn (9 chars) to their
  numerical values."
  [left-part]
  (map #(- (int %) 48) left-part))

(defn left-part-value
  "Computes the numerical value of the left part of the
  isbn (9 chars)."
  [left-part]
  (apply + (map *
                (left-part->nums left-part)
                (range 10 1 -1))))

(defn valid-right-char?
  "Checks if the rightmost char of the isbn is valid."
  [c]
  (or (= \X c)
      (<= 48 (int c) 57)))

(defn right-char-value
  "Returns the numerical value of the  rightmost char of
  the isbn."
  [c]
  (if (= \X c)
    10
    (- (int c) 48)))

(defn isbn?
  "Checks whether an isbn is valid."
  [isbn]
  (let [no-dashes (remove-dash isbn)
        left-part (butlast no-dashes)
        right-char (last no-dashes)]
    (if (and (valid-left-part? left-part)
             (valid-right-char? right-char))
      (let [lval (left-part-value left-part)
            rval (right-char-value right-char)]
        (= 0 (mod (+ lval rval) 11)))
      false)))
