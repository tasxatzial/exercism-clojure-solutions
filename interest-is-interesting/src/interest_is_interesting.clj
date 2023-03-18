(ns interest-is-interesting)

(defn abs
  [x]
  (if (< x 0)
    (- x)
    x))

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

(defn extra-money
  "Calculates the amount of money that will be added to the current
  balance, taking into account the interest rate. The amount can be
  negative or positive."
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
  (+ balance (extra-money balance)))

(defn amount-to-donate
  "Calculates how much money to donate to charities based on the
  balance and the tax-free percentage."
  [balance tax-free-percentage]
  (int (if (> balance 0)
         (* 2 balance (percentage->double tax-free-percentage))
         0)))
