(ns largest-series-product)

(defn validate-input
  [span digits]
  (cond
    (neg? span) (throw (IllegalArgumentException. "span must not be negative"))
    (> span (count digits)) (throw (IllegalArgumentException. "span must not exceed string length"))
    (not-every? #(<= 0 % 9) digits) (throw (IllegalArgumentException. "digits input must only contain digits"))
    :else true))

(defn largest-product
  "Returns the largest product of any consecutive digits of length span
  in the string s."
  [span s]
  (let [digits (map #(Character/digit ^char % 10) s)]
    (do
      (validate-input span digits)
      (->> digits
           (partition span 1)
           (map #(reduce * %))
           (apply max)))))