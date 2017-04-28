package uk.me.hofmann.permutator;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import uk.me.hofmann.permutator.guice.InjectLogger;
import uk.me.hofmann.permutator.jobfile.Job;
import uk.me.hofmann.permutator.jobfile.Jobfile;
import uk.me.hofmann.permutator.options.Options;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Permutator {

    private final Gson gson;

    @InjectLogger
    Logger logger;

    @Inject
    public Permutator(Gson gson) {
        this.gson = gson;
    }

    public int run(Options options) {
        logger.info("Starting properties file permutation...");

        Jobfile jobfile = readJobfile(options);
        Properties properties = readProperties(options);

        Set<PermutatedProperties> permutations = new HashSet<>();
        for (Job job: jobfile.jobs) {
             permutations.addAll(job.permutate(properties, options));
        }

        logger.info("Retrieved " + permutations.size() + " permuations.");

        for (PermutatedProperties permutation: permutations) {
            logger.info("Writing " + permutation.getName());
            writeFile(options.outputDirectory, permutation);
        }

        logger.info("... Finished!");
        return 0;
    }

    private Properties readProperties(Options options) {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(new File(options.configFileToPermutate))) {
            properties.load(reader);
        } catch (IOException e) {
            logger.error("Failed to read properties input!", e);
        }
        return properties;
    }

    private Jobfile readJobfile(Options options) {
        Jobfile jobfile = null;
        try (JsonReader reader = new JsonReader(new FileReader(new File(options.jobFile)))) {
            jobfile = gson.fromJson(reader, Jobfile.class);
        } catch (IOException e) {
            logger.error("Failed to parse job file!", e);
        }
        return jobfile;
    }

    private void writeFile(String outDirectoryPath, PermutatedProperties properties) {
        File outDirectory = new File(outDirectoryPath);
        if (!outDirectory.isDirectory()) {
            throw new IllegalArgumentException(outDirectory + " is not a directory.");
        }

        File outFile = new File(outDirectory, "config-" + properties.getName() + ".properties");
        try (FileOutputStream writer = new FileOutputStream(outFile)) {
            properties.getProperties().store(writer, "auto-generated config file");
        } catch (IOException e) {
            logger.error("Failed writing to file.", e);
        }
    }
}
