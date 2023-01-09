package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

public class defaultIfEmpty_SwitchIfEmpty {

    public static Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default");

    }

    public static Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Pineapple","Jack Fruit")
                        .transform(filterData));

    }

    public static void main(String[] args) {

        fruitsFluxTransformDefaultIfEmpty(6)
                .doFirst(()->System.out.print("Flux DefaultIfEmpty : "))
                .subscribe(v -> System.out.print(v+" "));

        fruitsFluxTransformSwitchIfEmpty(6)
                .doFirst(()->System.out.print("\nFlux SwitchIfEmpty : "))
                .subscribe(v -> System.out.print(v+" "));
    }
}
