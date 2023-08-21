(ns protein-translation)

;; non-lazy

(defn translate-codon
  [codon]
  (case codon
    "AUG" "Methionine"
    ("UUU" "UUC") "Phenylalanine"
    ("UUA" "UUG") "Leucine"
    ("UCU" "UCC" "UCA" "UCG") "Serine"
    ("UAU" "UAC") "Tyrosine"
    ("UGU" "UGC") "Cysteine"
    "UGG" "Tryptophan"
    ("UAA" "UGA" "UAG") "STOP"))

(defn translate-rna
  [s]
  (->> (range (/ (.length s) 3))
       (into [] (comp
                  (map #(subs s (* % 3) (* (inc %) 3)))
                  (map translate-codon)
                  (take-while #(not= "STOP" %))))))
