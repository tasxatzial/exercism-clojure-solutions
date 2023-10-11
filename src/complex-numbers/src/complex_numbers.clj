(ns complex-numbers)

(defn real
  "Returns the real part of the complex number c."
  [c]
  (first c))

(defn imaginary
  "Returns the imaginary part of the complex number c."
  [c]
  (second c))

(defn abs
  "Returns the absolute value of the complex number a + bI."
  [[a b]]
  (Math/sqrt (+ (* a a) (* b b))))

(defn conjugate
  "Returns the conjugate of the complex number a + bI."
  [[a b]]
  [a (- 0 b)])

(defn add
  "Adds a + bI to c + dI."
  [[a b] [c d]]
  [(+ a c) (+ b d)])

(defn sub
  "Subtracts c + dI from a + bI."
  [[a b] [c d]]
  [(- a c) (- b d)])

(defn mul
  "Multiples a + bI by c + dI."
  [[a b] [c d]]
  [(- (* a c) (* b d))
   (+ (* b c) (* a d))])

(defn div
  "Divides a + bI by c + dI."
  [[a b] [c d]]
  (let [denominator (+ (* c c) (* d d))]
    [(/ (+ (* a c) (* b d)) denominator)
     (/ (- (* b c) (* a d)) denominator)]))
