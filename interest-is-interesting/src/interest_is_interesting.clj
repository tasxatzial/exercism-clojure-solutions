(ns interest-is-interesting)

(defn abs
  [x]
  (if (< x 0)
    (- x)
    x))

(defn interest-rate
  "Calculates the interest rate based on the specified balance."
  [balance]
  (cond
    (neg? balance) -3.213
    (< balance 1000) 0.5
    (< balance 5000) 1.621
    :else 2.475))

(defn annual-balance-update
  "Calculates the annual balance update, taking into account the
  interest rate."
  [balance]
  (-> balance
      interest-rate
      bigdec
      (* (abs balance) 0.01M)                               ;(.abs balance) works too
      (+ balance)))

(defn amount-to-donate
  "Calculates how much money to donate to charities based on the
  balance and the tax-free percentage."
  [balance tax-free-percentage]
  (if (> balance 0)
    (int (* 0.02 balance tax-free-percentage))
    0))
