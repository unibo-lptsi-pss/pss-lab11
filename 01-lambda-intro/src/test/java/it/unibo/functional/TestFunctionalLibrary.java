package it.unibo.functional;

import it.unibo.functional.api.Function;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.functional.Transformers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link Transformers}.
 */
class TestFunctionalLibrary {

    private static <E, O> List<O> transform(final List<E> input, final Function<E, O> transformer) {
        final var result = new ArrayList<O>();
        for (final var element : input) {
            result.add(transformer.call(element));
        }
        return result;
    }

    private static final List<String> LOREM_IPSUM = List.of(
        "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt".split("\\s")
    );

    @Test
    void testIdentity() {
        final var input = List.of(1, 2, 3, 4, 5);
        final var identity = Function.<Integer>identity();
        var transformed = transform(input, identity);
        assertEquals(input, transformed);
    }
    @Test
    void testFlatten() {
        var input = List.of(
            List.of(1, 2, 3),
            List.of(4, 5, 6),
            List.of(7, 8, 9)
        );
        var flatten = flatten(input);
        var trueFlatten = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(trueFlatten, flatten);
    }

    @Test
    void testSelectAndReject() {
        final var startsWithAOrS = new Function<String, Boolean>() {
            @Override
            public Boolean call(final String input) {
                return input.startsWith("a") || input.startsWith("s");
            }
        };
        assertEquals(
            List.of("sit", "amet", "adipiscing", "sed"),
            select(LOREM_IPSUM, startsWithAOrS)
        );
        assertEquals(
            List.of("Lorem", "ipsum", "dolor", "consectetur", "elit", "do", "eiusmod", "tempor", "incididunt"),
            reject(LOREM_IPSUM, startsWithAOrS)
        );
    }
}
