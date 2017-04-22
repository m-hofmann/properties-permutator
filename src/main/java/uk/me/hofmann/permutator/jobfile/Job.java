package uk.me.hofmann.permutator.jobfile;

import com.google.common.collect.ImmutableSet;
import uk.me.hofmann.permutator.PermutatedProperties;

import java.util.*;

public class Job {

    public String name;

    public List<FixedListPermutation> permutations;

    public Set<PermutatedProperties> permutate(Properties origin) {
        Stack<FixedListPermutation> permutationStack = new Stack<>();
        permutationStack.addAll(permutations);

        PermutatedProperties startingPoint = new PermutatedProperties();
        startingPoint.setProperties(origin);
        startingPoint.setName(name);
        return permutateRecursive(ImmutableSet.of(startingPoint), permutationStack);
    }

    private Set<PermutatedProperties> permutateRecursive(Set<PermutatedProperties> sources,
                                                         Stack<FixedListPermutation> permutations) {
        FixedListPermutation currentPermutation = permutations.pop();
        Set<PermutatedProperties> result = new HashSet<>();

        for (PermutatedProperties sourceProperties: sources) {
            for (Object value: currentPermutation.values) {
                String permutatedName = createName(sourceProperties.getName(), currentPermutation.propertyName, value);
                PermutatedProperties newPermutation = new PermutatedProperties(sourceProperties);
                newPermutation.setName(permutatedName);
                if (value instanceof Double) {
                    Double asDouble = (Double) value;
                    if ((asDouble.equals(Math.floor(asDouble))) && !asDouble.isInfinite()) {
                        // hacky fix because JSON parses every number as Double and we sometimes have integers
                        Integer intValue = (int) Math.floor(asDouble);
                        newPermutation.getProperties().put(currentPermutation.propertyName, intValue.toString());
                    } else {
                        newPermutation.getProperties().put(currentPermutation.propertyName, value.toString());
                    }
                } else {
                    newPermutation.getProperties().put(currentPermutation.propertyName, value.toString());
                }
                result.add(newPermutation);
            }
        }

        if (!permutations.isEmpty()) {
            result = permutateRecursive(result, permutations);
        }

        return result;
    }

    private String createName(String originalName, String key, Object value) {
        return String.format("%s,%s-%s", originalName, key, value);
    }
}
