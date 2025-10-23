(ns secret-handshake)

(def msgs ["jump" "close your eyes" "double blink" "wink"])

(defn commands [n]
  (let [handshake (->> (map (partial bit-test n) (range 3 -1 -1))
                       (keep-indexed #(if (true? %2) (msgs %1))))]
    (if (> n 16)
      handshake
      (reverse handshake))))
