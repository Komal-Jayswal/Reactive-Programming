package net.learning;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class LoggingReactiveStreams {

    public static Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .log();
    }

    public static Mono<String> fruitMono() {
        return Mono.just("Mango")
                .log();
    }

    public static void main(String[] args) {


       fruitsFlux()
                .doFirst(()->System.out.println("Flux : "))
                .subscribe(v -> System.out.print(v+"\n"));

        fruitMono().doFirst(()->System.out.println("\nMono : ")).subscribe(System.out::println);
    }
}
