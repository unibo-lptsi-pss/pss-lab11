package it.unibo.functional;

import it.unibo.functional.api.Function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A special utility class with methods that transform collections using {@link Function}s provided as parameters.
 */
public final class Transformers {

    private Transformers() { }

    /**
     * A function that applies a transformation to each element of an {@link Iterable}, obtaining multiple elements,
     * @param base the elements on which to operate
     * @param transformer the {@link Function} to apply to each element. It must transform the elements into a
     * @return A mapped list of the produced elements
     * @param <I>
     * @param <O>
     */
    public static <I, O> List<O> map(final Iterable<? extends I> base, final Function<I, O> transformer) {
        final var result = new ArrayList<O>();
        for (final I input : base) {
            result.add(transformer.call(input));
        }
        return result;
    }
    /**
     * A function that takes an iterable of collections, and returns a flatten list of the elements of the inner
     * collections.
     * For instance, {@code [[1], [2, 3], [4, 5], []]} could use {@code flatten} to produce a flat list, thus obtaining
     * {@code [1, 2, 3, 4, 5]}.
     *
     * @param base the collections on which to operate
     * @return A flattened list with the elements of each collection in the input
     * @param <I> type of the collection elements
     */
    public static <I> List<I> flatten(final Iterable<? extends Collection<I>> base) {
        // Suggestion: follow the schema of map, thus creating a new list and adding elements to it
        var result = new ArrayList<I>();
        for (final Collection<I> input : base) {
            result.addAll(input);
        }
        return result;
    }

    /**
     * A function that applies a test to each element of an {@link Iterable}, returning a list containing only the
     * elements that pass the test.
     * For instance, {@code [1, 2, 3, 4, 5]} could use {@code select} to filter only the odd numbers, thus obtaining
     * {@code [1, 3, 5]}.
     *
     * @param base the elements on which to operate
     * @param test the {@link Function} to use to test whether the elements should be selected.
     * @return A list containing only the elements that passed the test
     * @param <I> elements type
     */
    public static <I> List<I> select(final Iterable<I> base, final Function<I, Boolean> test) {
        var result = new ArrayList<I>();
        for (final I input : base) {
            if (test.call(input)) {
                result.add(input);
            }
        }
        return result;
    }

    /**
     * A function that applies a test to each element of an {@link Iterable}, returning a list containing only the
     * elements that do not pass the test.
     * For instance, {@code [1, 2, 3, 4, 5]} could use {@code select} to reject all the even numbers, thus obtaining
     * {@code [1, 3, 5]}.
     * <b>NOTE:</b> this function is a special select whose test function return value is negated.
     *
     * @param base the elements on which to operate
     * @param test the {@link Function} to use to test whether the elements should be discarded.
     * @return A list containing only the elements that passed the test
     * @param <I> elements type
     */
    public static <I> List<I> reject(final Iterable<I> base, final Function<I, Boolean> test) {
        /**
         * Suggestion: try to implement this function using `select`.
         * As exercise, use both anonymous classes and lambda notation.
         */
        return select(base, new Function<I, Boolean>() {
            @Override
            public Boolean call(final I input) {
                return !test.call(input);
            }
        });
    }
}
