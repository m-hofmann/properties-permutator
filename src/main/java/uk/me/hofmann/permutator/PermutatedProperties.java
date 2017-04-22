package uk.me.hofmann.permutator;

import java.util.Properties;

public class PermutatedProperties {

    private String name;

    private Properties properties;

    public PermutatedProperties() {
        this.name = "";
        this.properties = new Properties();
    }

    public PermutatedProperties(PermutatedProperties other) {
        this.name = other.getName();
        this.properties = new Properties();
        this.properties.putAll(other.getProperties());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "PermutatedProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
