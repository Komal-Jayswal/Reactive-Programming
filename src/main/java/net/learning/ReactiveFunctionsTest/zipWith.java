package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class zipWith {

    public static Flux<String> fluxZipWith() {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon");

        return fruits.zipWith(veggies,
                (first,second) -> first+second);
    }

    public static Flux<String> fluxZipTuple() {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon");
        var moreVeggies = Flux.just("Potato","Beans");

        return Flux.zip(fruits,veggies,moreVeggies)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3());
    }

    public static Mono<String> monoZipWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");

        return fruits.zipWith(veggies,
                (first,second) -> first+second);
    }

    public static void main(String[] args) {

        fluxZipWith()
                .doFirst(()->System.out.print("Flux zipWith : "))
                .subscribe(v -> System.out.print(v+" "));

        fluxZipTuple()
                .doFirst(()->System.out.print("\nFlux zipWith with tuple : "))
                .subscribe(v -> System.out.print(v+" "));

        monoZipWith()
                .doFirst(()->System.out.print("\nMono zipWith : "))
                .subscribe(v -> System.out.print(v+" "));
    }
}
