(ns secret-handshake)

(defn to-binary
  "Converts n to its binary representation, that is, a list of 0 and 1."
  [n]
  (loop [result ()
         n n]
    (if (zero? n)
      (or (seq result) '(0))
      (let [r (rem n 2)
            q (quot n 2)]
        (recur (conj result r) q)))))

(defn zero-prefix
  "Prepends the required number of zeros to coll so that its
  final size is n."
  [n coll]
  (concat (take (- n (count coll)) (repeat 0))
          coll))

(def index->msg
  {3 "wink"
   2 "double blink"
   1 "close your eyes"
   0 "jump"})

(defn commands [n]
  (let [binary (zero-prefix 5 (to-binary n))
        handshake (keep-indexed #(if (= 1 %2) (get index->msg %1))
                                (take-last 4 binary))]
    (if (> n 16)
      handshake
      (reverse handshake))))
