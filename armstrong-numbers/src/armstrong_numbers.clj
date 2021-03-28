(ns armstrong-numbers)

(defn armstrong? [num]
  (def s (->> num str (map (comp read-string str))))
  (= num (apply + (map #(reduce * (repeat (count s) %)) s))))
