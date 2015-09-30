# Alignment

A program for Computational Biology/Bioinformatics class. This Java program will use the Smith-Waterman Global Alignment algorithm to find the best global alignment of any two given strings.

## Usage

To run this program when compiled, call Alignment.main.

## Notice

The results of the run are written by default to logs_(iterations)x(lengths).txt in the instance's local directory. This can be changed, but is currently hard-coded.

## Warning

Do not run this program with multiple threads of a single instance. Static variables are used to keep it functional, and running these in parallel will likely corrupt results.