# Flatten Array

Welcome to Flatten Array on Exercism's Clojure Track.
If you need help running the tests or submitting your code, check out `HELP.md`.

## Introduction

A shipment of emergency supplies has arrived, but there's a problem.
To protect from damage, the items — flashlights, first-aid kits, blankets — are packed inside boxes, and some of those boxes are nested several layers deep inside other boxes!

To be prepared for an emergency, everything must be easily accessible in one box.
Can you unpack all the supplies and place them into a single box, so they're ready when needed most?

## Instructions

Take a nested array of any depth and return a fully flattened array.

Note that some language tracks may include null-like values in the input array, and the way these values are represented varies by track.
Such values should be excluded from the flattened array.

Additionally, the input may be of a different data type and contain different types, depending on the track.

Check the test suite for details.

## Example

input: `[1, [2, 6, null], [[null, [4]], 5]]`

output: `[1, 2, 6, 4, 5]`

## Appendix

~~~~exercism/note
The instructions above are synchronized with a shared repository to ensure consistency across all language tracks.
This appendix provides additional clarification or modifies the instructions as needed to better align with the goals of the Clojure track.
~~~~

For this exercise in the Clojure track, you may **assume the input is any nested combination of vectors, and the output is a vector**, as indicated by the tests.
However, this is not a strict requirement; you are free to assume that the input is any nested combination of sequential things (lists, vectors, etc.), and the output is a sequence.

## Source

### Created by

- @haus

### Contributed to by

- @AndreaCrotti
- @mwfogleman
- @object88
- @sjwarner-bp
- @yurrriq
- @tasxatzial

### Based on

Interview Question - https://reference.wolfram.com/language/ref/Flatten.html