(ns largest-series-product)

(defn int->digits
  "Converts an integer to a seq of its digits."
  [s]
  (map #(Integer/parseInt %)
       (map str s)))

(defn largest-product
  "Calculates the largest product for a contiguous substring of digits
  of length n."
  [n s]
  (let [digits (int->digits s)]
    (if (> n (count digits))
      (throw (Exception.))
      (loop [max 0
             remaining-digits digits]
        (if (>= (count remaining-digits) n)
          (let [mult (apply * (take n remaining-digits))]
            (if (> mult max)
              (recur mult (rest remaining-digits))
              (recur max (rest remaining-digits))))
          max)))))
