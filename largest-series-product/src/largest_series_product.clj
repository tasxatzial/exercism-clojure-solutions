(ns largest-series-product)

(defn int->digits
  "Converts an integer to a seq of its digits."
  [s]
  (map #(Character/digit ^char % 10)
       (str s)))

(defn valid-digits?
  "Checks if the digits seq represents a valid integer."
  [digits]
  (every? #(<= 0 % 9)
          digits))

(defn largest-product
  "Calculates the largest product for a contiguous substring of digits
  of length n."
  [n s]
  (let [digits (int->digits s)]
    (if (or (> n (count digits))
            (< n 0)
            (not (valid-digits? digits)))
      (throw (Exception.))
      (if (= 0 n)
        1
        (loop [max 0
               remaining-digits digits]
          (if (>= (count remaining-digits) n)
            (let [mult (apply * (take n remaining-digits))]
              (if (> mult max)
                (recur mult (rest remaining-digits))
                (recur max (rest remaining-digits))))
            max))))))
