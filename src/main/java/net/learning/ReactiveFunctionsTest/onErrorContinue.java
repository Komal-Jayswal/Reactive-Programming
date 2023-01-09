package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;

public class onErrorContinue {

    public static Flux<String> fluxOnErrorContinue() {
        return Flux.just("Apple","Mango","Orange")
                .map(object -> {
                    if (object.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("\nException Occurred");
                    return object.toUpperCase();
                })
                .onErrorContinue((throwable,object) -> {
                    System.out.println("\nThrowable = " + throwable);
                    System.out.println("Next Object = " + object);
                });
    }

    public static void main(String[] args) {

        fluxOnErrorContinue()
                .doFirst(()->System.out.print("\nFlux onErrorContinue -> \n\n"))
                .subscribe(v -> System.out.print(v+" "));
    }
}
