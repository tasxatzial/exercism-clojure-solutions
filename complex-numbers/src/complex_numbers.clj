(ns complex-numbers)

(defn real
  "Returns the real part of a complex number a + bI."
  [[a _]]
  a)

(defn imaginary
  "Returns the imaginary part of a complex number a + bI."
  [[_ b]]
  b)

(defn abs
  "Returns the absolute value of a complex number a + bI."
  [[a b]]
  (Math/sqrt (+ (* a a) (* b b))))

(defn conjugate
  "Returns the conjugate of a complex number a + bI."
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
  (let [denom (float (+ (* c c) (* d d)))]
    [(/ (+ (* a c) (* b d)) denom)
     (/ (- (* b c) (* a d)) denom)]))
