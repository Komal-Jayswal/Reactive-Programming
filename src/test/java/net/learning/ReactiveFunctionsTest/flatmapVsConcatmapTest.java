package net.learning.ReactiveFunctionsTest;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import static net.learning.ReactiveFunctionsTest.flatmapVsConcatmap.fruitsFluxConcatMap;
import static net.learning.ReactiveFunctionsTest.flatmapVsConcatmap.fruitsFluxFlatMapAsync;

public class flatmapVsConcatmapTest {

    @Test
    void fruitsFluxFlatMapAsyncTest() {

        StepVerifier.create(fruitsFluxFlatMapAsync())
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMapTest() {

        StepVerifier.create(fruitsFluxConcatMap())
                .expectNextCount(17)
                .verifyComplete();
    }
}
