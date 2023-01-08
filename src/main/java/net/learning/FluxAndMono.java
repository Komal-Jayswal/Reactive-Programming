package net.learning;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

public class FluxAndMono {
    public static Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango","Orange","Banana"));
    }

    public static Mono<String> fruitMono() {
        return Mono.just("Mango");
    }

    public static void main(String[] args) {

        fruitsFlux()
                .doFirst(()->System.out.print("Flux : "))
                .subscribe(v -> System.out.print(v+" "));

        fruitMono().doFirst(()->System.out.print("\nMono : ")).subscribe(System.out::println);
    }

}


