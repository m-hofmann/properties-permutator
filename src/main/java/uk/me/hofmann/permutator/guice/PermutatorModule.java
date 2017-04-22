package uk.me.hofmann.permutator.guice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class PermutatorModule extends AbstractModule {
    protected void configure() {
        bindListener(Matchers.any(), new Log4JTypeListener());
        bind(Gson.class).toInstance(new GsonBuilder()
                .setPrettyPrinting()
                .create());
    }
}
