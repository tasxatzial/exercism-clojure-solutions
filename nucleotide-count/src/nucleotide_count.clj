(ns nucleotide-count)

(def nucleotides #{\A \C \T \G})

(defn count-of-nucleotide-in-strand
  [nucleotide strand]
  (if (not (contains? nucleotides nucleotide))
    (throw (Exception.))
    (count (filter #{nucleotide} strand))))

(defn nucleotide-counts
  "Returns a map of {nucleotide -> nucleotide count} for all
  nucleotides in the given strand."
  [strand]
  (reduce (fn [result nucleotide]
            (let [nucleotide-count (count-of-nucleotide-in-strand nucleotide strand)]
              (assoc result nucleotide nucleotide-count)))
          {}
          nucleotides))
