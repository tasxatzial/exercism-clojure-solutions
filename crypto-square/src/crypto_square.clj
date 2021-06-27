(ns crypto-square)

;; tests cipher-3 and cipher-5 fail, they are using
;; incorrectly normalized ciphertexts

(defn normalize-plaintext
  "Normalizes a string: removes spaces & punctuation, converts
  to lowercase."
  [s]
  (-> s
      (clojure.string/replace #"[^A-Za-z0-9]" "")
      clojure.string/lower-case))

(defn square-size
  "Returns the columns of the rectangle that will be used
   for organizing the normalized version of the given string."
  [s]
  (let [rounded-sqrt (-> s
                        normalize-plaintext
                        count
                        Math/sqrt
                        Math/round)]
    (if (>= (* rounded-sqrt rounded-sqrt) (count s))
      rounded-sqrt
      (inc rounded-sqrt))))

(defn plaintext-segments
  "Returns a vector of strings, this is essentially the
  normalized version of the given string organized in rows."
  [s]
  (let [norm-s (normalize-plaintext s)
        cols (square-size s)]
    (mapv #(apply str %) (partition-all cols cols norm-s))))

(defn ciphertext
  "Returns the encoded text of the given string."
  [s]
  (let [norm-s (normalize-plaintext s)
        segments (plaintext-segments norm-s)]
    (loop [result []
           segments segments]
      (if (seq (first segments))
        (recur (into result (mapv first segments))
               (map rest segments))
        (apply str result)))))

(defn pad
  "Replaces nil items with spaces and adds an
  extra space at the end of coll."
  [coll]
  (conj (reduce (fn [result x]
                  (if (nil? x)
                    (conj result \space)
                    (conj result x)))
                []
                coll)
        \space))

(defn normalize-ciphertext
  "Returns a normalized version of the encoded text
  of the given string."
  [s]
  (let [norm-s (normalize-plaintext s)
        segments (plaintext-segments norm-s)]
    (loop [result []
           segments segments]
      (if (seq (first segments))
        (recur (into result (pad (mapv first segments)))
               (map rest segments))
        (apply str (butlast result))))))
