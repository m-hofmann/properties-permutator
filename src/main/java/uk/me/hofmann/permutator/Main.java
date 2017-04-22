package uk.me.hofmann.permutator;

import com.beust.jcommander.JCommander;
import com.google.inject.Guice;
import com.google.inject.Injector;
import uk.me.hofmann.permutator.guice.PermutatorModule;
import uk.me.hofmann.permutator.options.Options;

public class Main {

    public static void main(String ...argv) {
        Options options = new Options();
        JCommander.newBuilder()
                .addObject(options)
                .build()
                .parse(argv);

        Injector injector = Guice.createInjector(new PermutatorModule());
        Permutator permutator = injector.getInstance(Permutator.class);

        permutator.run(options);
    }
}
