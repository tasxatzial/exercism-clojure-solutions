(ns phone-number)

(def invalid-phone "0000000000")

(defn digit?
  "Returns true if character c represents a digit,
  false otherwise."
  [c]
  (<= 48 (int c) 57))

(defn string->digits
  "Cleans up a string phone number and returns a vector of
  its digits."
  [s]
  (let [cleaned (filter digit? s)]
    (mapv #(Character/digit ^char % 10) cleaned)))

(defn- number-10digit
  "Converts a digits vector that represents a clean 10 digit
  phone number to a string.
  Returns \"0000000000\" if at least one of the following holds:
  - The first digit of the area code is < 2
  - The first digit of the exchange code is < 2"
  [digits]
  (if (or (< (first digits) 2)
          (< (nth digits 3) 2))
    invalid-phone
    (apply str digits)))

(defn- number-11digit
  "Converts the given vector that represents a clean 11 digit
  phone number to a string.
  Returns \"0000000000\" if at least one of the following holds:
  - The first digit of the area code is < 2
  - The first digit of the exchange code is < 2
  - The country code is not 1"
  [digits]
  (if (not= 1 (first digits))
    invalid-phone
    (number-10digit (rest digits))))

(defn number
  "Cleans up a string phone number and returns the
  number represented by a string of 10 digit characters.
  Returns \"0000000000\" if the string does not represent
  a valid phone number."
  [s]
  (let [digits (string->digits s)]
    (case (count digits)
      11 (number-11digit digits)
      10 (number-10digit digits)
      invalid-phone)))

(defn area-code
  "Returns the area code of a string phone number."
  [s]
  (let [digits (string->digits s)]
    (if (= 11 (count digits))
      (apply str (subvec digits 1 4))
      (apply str (subvec digits 0 3)))))

(defn- remove-country-code
  "Removes the country code from a digits vector
  that represents a phone number."
  [digits]
  (if (= 11 (count digits))
    (rest digits)
    digits))

(defn pretty-print
  "Formats a string phone number as (XXX) XXX-XXXX.
  (XXX) represents the area-code, the XXX before the dash
  represents the exchange code."
  [s]
  (let [digits (-> s
                   string->digits
                   remove-country-code)
        ar-code (apply str (take 3 digits))
        ex-code (apply str (take 3 (drop 3 digits)))
        remaining (apply str (drop 6 digits))]
    (str "(" ar-code ") " ex-code "-" remaining)))
