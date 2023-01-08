package net.learning.ReactiveFunctionsTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Transform the item emitted by this Mono into a Publisher, then forward its emissions into the returned Flux.
 */
public class flatMapMany {
    public static Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")));
    }

    public static void main(String[] args) {

        fruitMonoFlatMapMany()
                .doFirst(()->System.out.print("\nConvert Mono to Flux : "))
                .subscribe(System.out::print);
    }

}
