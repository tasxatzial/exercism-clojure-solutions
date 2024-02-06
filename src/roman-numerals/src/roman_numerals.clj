(ns roman-numerals)

(def static-convert
  {1000 "M" 900 "CM" 500 "D" 400 "CD"
   100 "C" 90 "XC" 50 "L" 40 "XL"
   10 "X" 9 "IX" 5 "V" 4 "IV"
   1 "I"})

(def static-decimals
  (sort > (keys static-convert)))

(defn numerals
  [num]
  (loop [static-decimals static-decimals
         num num
         res ""]
    (if (seq static-decimals)
      (let [decimal (first static-decimals)
            roman (static-convert decimal)
            q (quot num decimal)
            new-num (- num (* q decimal))
            new-res (apply str res (repeat q roman))]
        (recur (rest static-decimals) new-num new-res))
      res)))