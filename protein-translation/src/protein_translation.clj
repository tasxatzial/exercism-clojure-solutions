(ns protein-translation)

(def codon->protein
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

(defn partition-into-codons
  "Partitions the given string into a sequence of codons."
  [s]
  (->> s
       (partition 3)
       (map #(apply str %))))

(defn translate-codon
  [s]
  (get codon->protein s))

(defn translate-rna
  [s]
  (->> s
       partition-into-codons
       (map codon->protein)
       (take-while #(not= "STOP" %))))
