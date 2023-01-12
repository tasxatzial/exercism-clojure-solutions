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
  (let [chars-seq (seq s)]
    (if (valid-dna? chars-seq)
      (apply str (map dna->rna chars-seq))
      (throw (AssertionError.)))))
