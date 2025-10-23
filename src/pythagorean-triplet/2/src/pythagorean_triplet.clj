(ns pythagorean-triplet)

;; Time complexity: O(n)

(defn find-pythagorean-triplets
  "Given an integer N, it returns all Pythagorean triplets for which a + b + c = N"
  [N]
  (for [a (range N)
        :let [b (/ (* N (- N (* 2 a))) (* 2 (- N a)))
              c (- N a b)]
        :when (and (pos-int? b)
                   (< a b c)
                   (= (* c c) (+ (* a a) (* b b))))]
    [a b c]))