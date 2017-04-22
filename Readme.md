# Java .properties Permutator

Permutate configuration options from `properties` files.

## About

For testing an application, it is often necessary to evaluate it using a range of parameters. This tool allows to create config file permutations based on a default configuration file and a job file that describes the permutations.

The tool creates `n` output `.properties` files based on all parameters given in the job file. Values not modified by the job file are passed through to the output files.

Usage: `java -jar permutator.jar -config default.properties -jobfile myjob.json -outdir .`

See `src/main/resources/jobfile.json` for an example of a job file.

## Regarding Features/Bugs

The tool is provided as-is, as a working implementation was the focus of its creation.
