(ns phone-number)

(defn number
  "Removes any spaces, symbols and the country code from a phone number.
  If the number is not valid, it returns 0000000000."
  [s]
  (if (re-matches #"\+?1?[\.|\-| ]?(\([2-9]\d{2}\)|[2-9]\d{2})[\.|\-| ]?[2-9]\d{2}[\.|\-| ]?\d{4}" s)
    (let [digits (re-seq #"\d" s)]
      (if (= 11 (count digits))
        (apply str (rest digits))
        (apply str digits)))
    "0000000000"))

(defn area-code
  "Extracts the area code from a phone number."
  [s]
  (-> s
      number
      (subs 0 3)))

(defn pretty-print
  "Formats a phone number as (NXX) NXX-XXXX."
  [s]
  (let [n (number s)]
    (format "(%s) %s-%s" (subs n 0 3) (subs n 3 6) (subs n 6))))
