package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;

import java.util.List;

public class filter {

    public static Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s -> s.length() == number);
    }

    public static Flux<String> fruitsFluxFilterMap(int number) {
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s -> s.length() == number)
                .map(String::toUpperCase);
    }

    public static void main(String[] args) {

        fruitsFluxFilter(5)
                .doFirst(()->System.out.print("Flux Filter : "))
                .subscribe(v -> System.out.print(v+" "));

        fruitsFluxFilterMap(6)
                .doFirst(()->System.out.print("\nFlux Filter Map : "))
                .subscribe(v -> System.out.print(v+" "));
    }
}
