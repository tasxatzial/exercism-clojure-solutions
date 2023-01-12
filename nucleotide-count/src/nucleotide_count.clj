(ns nucleotide-count)

(def nucleotides #{\A \C \T \G})

(defn count-of-nucleotide-in-strand
  [nucleotide strand]
  (if (not (contains? nucleotides nucleotide))
    (throw (Exception.))
    (count (filter #{nucleotide} strand))))

(defn nucleotide-counts
  [strand]
  (reduce (fn [result nucleotide]
            (let [nucleotide-count (count-of-nucleotide-in-strand nucleotide strand)]
              (assoc result nucleotide nucleotide-count)))
          {}
          nucleotides))
