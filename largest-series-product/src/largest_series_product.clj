(ns largest-series-product)

(defn string-int->digits
  "Converts a string that represents a non-negative integer
  to a sequence of its digits (integers)."
  [s]
  (map #(Character/digit ^char % 10) s))

(defn valid-digits?
  "Returns true if the given collection contains only integers
  between 0 and 9, false otherwise."
  [digits]
  (every? #(<= 0 % 9) digits))

(defn largest-product
  [n s]
  (let [digits (string-int->digits s)]
    (if (or (neg? n)
            (and (pos? n) (empty? digits))
            (not (valid-digits? digits)))
      (throw (Exception.))
      (if (zero? n)
        1
        (->> digits
             (partition n 1)
             (map #(reduce * %))
             (apply max))))))
