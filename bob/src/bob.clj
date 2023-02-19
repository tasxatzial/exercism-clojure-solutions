(ns bob)

(defn question?
  [s]
  (clojure.string/ends-with? s "?"))

(defn yell?
  [s]
  (and (= s (clojure.string/upper-case s))
       (boolean (re-find #"[A-Za-z]" s))))

(defn silence?
  [s]
  (clojure.string/blank? s))

(defn yell-question?
  [s]
  (and (yell? s) (question? s)))

(defn response-for
  [s]
  (let [trim-s (clojure.string/trim s)]
    (condp apply [trim-s]
      silence? "Fine. Be that way!"
      yell-question? "Calm down, I know what I'm doing!"
      yell? "Whoa, chill out!"
      question? "Sure."
      "Whatever.")))
