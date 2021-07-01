(ns protein-translation)

(def condon->protein
  {"AUG" "Methionine"
   "UUU" "Phenylalanine"
   "UUC" "Phenylalanine"
   "UUA" "Leucine"
   "UUG" "Leucine"
   "UCU" "Serine"
   "UCC" "Serine"
   "UCA" "Serine"
   "UCG" "Serine"
   "UAU" "Tyrosine"
   "UAC" "Tyrosine"
   "UGU" "Cysteine"
   "UGC" "Cysteine"
   "UGG" "Tryptophan"
   "UAA" "STOP"
   "UGA" "STOP"
   "UAG" "STOP"})

(defn translate-codon
  "Translates a condon to protein name or STOP."
  [condon]
  (get condon->protein condon))

(defn translate-rna
  "Translates a rna string to a protein name sequence."
  [s]
  (let [condons (partition 3 s)]
    (loop [result []
           [condon & more] condons]
      (if condon
        (let [protein (get condon->protein (apply str condon))]
          (if (= "STOP" protein)
            result
            (recur (conj result protein) more)))
        result))))
