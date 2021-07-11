(ns largest-series-product)

(defn int->digits
  "Converts an integer to a seq of its digits."
  [s]
  (map #(Character/digit ^char % 10) s))

(defn valid-digits?
  "Checks if the digits seq represents a valid integer."
  [digits]
  (every? #(<= 0 % 9) digits))

(defn largest-product
  "Calculates the largest product for a contiguous substring of digits
  of length n."
  [n s]
  (if (< n 0)
    (throw (Exception.))
    (let [digits (int->digits s)]
      (if (or (> n (count digits))
              (not (valid-digits? digits)))
        (throw (Exception.))
        (if (= 0 n)
          1
          (apply max (map #(apply * %) (partition n 1 digits))))))))
