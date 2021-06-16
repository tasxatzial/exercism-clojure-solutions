(ns rna-transcription)

(def dna->rna
  {:G \C, :C \G, :T \A, :A \U})

(defn dna-keywords
  [dna]
  (map (comp keyword str) dna))

(defn valid-dna-keywords?
  [dna-keywords]
  (every? dna->rna dna-keywords))

(defn to-rna
  "Given a DNA strand, return its RNA complement (per RNA transcription)."
  [dna]
  (let [keywords (dna-keywords dna)]
    (if (valid-dna-keywords? keywords)
      (apply str (map dna->rna keywords))
      (throw (AssertionError.)))))
