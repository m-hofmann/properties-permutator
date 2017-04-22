package uk.me.hofmann.permutator.options;

import com.beust.jcommander.Parameter;

public class Options {

    @Parameter(names = {"-config"}, description = "Path to config file that should be used as basis for permutation.", required = true)
    public String configFileToPermutate;

    @Parameter(names = {"-jobfile"}, description = "Path to jobfile that describes permutations for the config file.", required = true)
    public String jobFile;

    @Parameter(names = {"-outdir"}, description = "Path to the directory the output should be written to", required = true)
    public String outputDirectory;
}
