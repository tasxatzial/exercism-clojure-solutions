(ns rna-transcription)

(def dna->rna
  {\G \C,
   \C \G,
   \T \A,
   \A \U})

(defn valid-dna?
  [keywords]
  (every? dna->rna keywords))

(defn to-rna
  [s]
  (if (valid-dna? s) 
    (apply str (map dna->rna s)) 
    (throw (AssertionError.))))
