(ns phone-number)

(def invalid-number "0000000000")

(defn number
  [s]
  (let [s-digits (clojure.string/replace s #"\D" "")]
    (if (re-matches #"1?[2-9]\d{2}[2-9]\d{6}" s-digits)
      (if (= 11 (.length s-digits))
        (subs s-digits 1)
        s-digits)
      invalid-number)))
