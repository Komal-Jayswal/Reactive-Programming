package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class flatmapVsConcatmap {

    public static Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .flatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }

    public static Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .concatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }
}
