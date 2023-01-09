package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

/**
 * Following functions are used to transform Flux/Mono to generate a target Flux/Mono:
 */
public class transform {

    public static Flux<String> fruitsFluxTransform(int number) {

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData);
        //.filter(s -> s.length() > number);
    }

    public static void main(String[] args) {

        fruitsFluxTransform(5)
                .doFirst(()->System.out.print("Flux transform : "))
                .subscribe(v -> System.out.print(v+" "));
    }
}
