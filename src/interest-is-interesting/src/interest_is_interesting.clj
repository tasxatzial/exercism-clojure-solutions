(ns interest-is-interesting)

(defn percentage->double
  [percentage]
  (* 0.01 percentage))

(defn interest-rate
  "Calculates the interest rate based on the specified balance.
  The rate represents a percentage, e.g. 0.5 is 0.5 percent."
  [balance]
  (cond
    (neg? balance) -3.213
    (< balance 1000) 0.5
    (< balance 5000) 1.621
    :else 2.475))

(defn annual-balance-diff
  "Calculates the annual balance difference."
  [balance]
  (-> balance
      interest-rate
      percentage->double
      bigdec
      (* (abs balance))))

(defn annual-balance-update
  "Calculates the annual balance update, taking into account the
  interest rate."
  [balance]
  (+ balance (annual-balance-diff balance)))

(defn amount-to-donate
  "Calculates how much money to donate to charities based on the
  balance and the tax-free percentage."
  [balance tax-free-percentage]
  (int (if (> balance 0)
         (* 2 balance (percentage->double tax-free-percentage))
         0)))
