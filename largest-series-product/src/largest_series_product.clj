(ns largest-series-product)

(defn invalid-input?
  [n digits]
  (or (neg? n)
      (and (pos? n) (empty? digits))
      (> n (count digits))
      (not-every? #(<= 0 % 9) digits)))

(defn largest-product
  [n s]
  (let [digits (map #(Character/digit ^char % 10) s)]
    (if (invalid-input? n digits)
      (throw (Exception.))
      (if (zero? n)
        1
        (->> digits
             (partition n 1)
             (map #(reduce * %))
             (apply max))))))
