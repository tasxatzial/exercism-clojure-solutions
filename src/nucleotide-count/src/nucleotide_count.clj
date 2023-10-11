(ns nucleotide-count)

(def nucleotides #{\A \C \T \G})

(defn count-of-nucleotide-in-strand
  [nucleotide strand]
  (if (contains? nucleotides nucleotide)
    (count (filter #{nucleotide} strand))
    (throw (Exception.))))

(defn nucleotide-counts
  "Returns a map of {nucleotide -> nucleotide count} for all
  nucleotides in the given strand."
  [strand]
  (reduce (fn [result nucleotide]
            (->> strand
                 (count-of-nucleotide-in-strand nucleotide)
                 (assoc result nucleotide)))
          {}
          nucleotides))
