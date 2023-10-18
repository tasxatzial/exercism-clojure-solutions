(ns bank-account)

(defn open-account
  []
  (atom 0))

(defn close-account
  [account]
  (swap! account (fn [& args] nil)))

(defn get-balance
  [account]
  @account)

(defn update-balance
  [account amount]
  (swap! account + amount))
