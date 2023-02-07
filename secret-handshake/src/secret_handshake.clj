(ns secret-handshake)

(defn to-binary
  "Returns a list the represents the binary form of n."
  [n]
  (loop [result ()
         n n]
    (if (zero? n)
      (or (seq result) '(0))
      (let [r (rem n 2)
            q (quot n 2)]
        (recur (conj result r) q)))))

(defn zero-prefix
  "Adds the required number of 0 in front of coll so that its
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
