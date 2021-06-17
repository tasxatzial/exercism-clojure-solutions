(ns phone-number)

(defn string->digits
  "Cleans up a phone number and returns its digits list."
  [num-string]
  (let [char->int (map int num-string)
        cleaned (filter #(<= 48 (int %) 57) char->int)]
    (map #(- % 48) cleaned)))

(defn- number-10digit
  "Returns \"0000000000\" if the digits list does not represent
  a valid 10 digit phone number, else it returns the phone number."
  [digits]
  (if (or (< (first digits) 2)
          (< (nth digits 3) 2))
    "0000000000"
    (apply str digits)))

(defn- number-11digit
  "Returns \"0000000000\" if the digits list does not represent
  a valid 11 digit phone number, else it returns the phone number."
  [digits]
  (if (not= 1 (first digits))
    "0000000000"
    (number-10digit (rest digits))))

(defn number
  "Returns \"0000000000\" if the digits list does not represent
  a valid phone number, else it returns the phone number."
  [num-string]
  (let [digits (string->digits num-string)
        digit-count (count digits)]
    (case digit-count
      11 (number-11digit digits)
      10 (number-10digit digits)
      "0000000000")))

(defn area-code
  "Returns the area code of a phone number."
  [num-string]
  (let [digits (string->digits num-string)]
    (if (= 11 (count digits))
      (apply str (take 3 (drop 1 digits)))
      (apply str (take 3 digits)))))

(defn- remove-country-code
  "Removes the country code from 11 digit phone numbers."
  [digits]
  (if (= 11 (count digits))
    (rest digits)
    digits))

(defn pretty-print
  "Formats a phone number as (XXX) XXX-XXXX.
  (XXX) represents the area-code, the XXX before the dash
  represents the exchange code."
  [num-string]
  (let [digits (-> num-string
                   string->digits
                   remove-country-code)
        ar-code (apply str (take 3 digits))
        ex-code (apply str (take 3 (drop 3 digits)))
        remaining (apply str (drop 6 digits))]
    (str "(" ar-code ") " ex-code "-" remaining)))
