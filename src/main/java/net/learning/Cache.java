package net.learning;

import reactor.core.publisher.Mono;

public class Cache {

    public static void cacheExample() {
            var mono = Mono.fromCallable(() -> {
                        System.out.println("Go!");
                        return 5;
                    })
                    .map(i -> {
                        System.out.println("Double!");
                        return i * 2;
                    });

            var cached = mono.cache();

            System.out.println("Using cached");

            System.out.println("1. " + cached.block());
            System.out.println("2. " + cached.block());
            System.out.println("3. " + cached.block());

            System.out.println("Using NOT cached");


            System.out.println("1. " + mono.block());
            System.out.println("2. " + mono.block());
            System.out.println("3. " + mono.block());
    }

    public static void main(String[] args) {

        cacheExample();
    }
}
