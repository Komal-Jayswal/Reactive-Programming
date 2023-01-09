package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;

public class onErrorReturn {

    public static Flux<String> fluxOnErrorReturn() {
        return Flux.just("Telikos","OHM")
                .concatWith(Flux.error(
                        new RuntimeException("Exception Occurred")
                ))
                .onErrorReturn("Maersk");
    }

    public static void main(String[] args) {

        fluxOnErrorReturn()
                .doFirst(()->System.out.print("Flux OnErrorReturn : "))
                .subscribe(v -> System.out.print(v+" "));
    }
}
