package net.learning.ReactiveFunctions.mapVsflatMap;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapVsFlatMap {

    /**
     * Map in Funtional Programming
     */
    public static void map_Functional(){

        List<Customer> customer2 = DataBase.getAll().block();

        List<String> emails2 = customer2 // List<Customer>
                .stream()  //Stream<Customer>
                .map(Customer::getEmail) // Stream<Object> i.e Stream<String>
                .toList(); // List<Object> i.e List<String>

        System.out.println("\n Functional Map : \n "+ emails2);
    }

    /**
     * Map in Reactive Programming
     */
    public static void map_Reactive(){

        Mono<List<Customer>> customers = DataBase.getAll();

        Mono<List<String>> emails = customers //Mono<List<Customer>>
                .map(customers1 // List<Customer>
                        -> customers1.stream() // Stream<Customer>
                        .map(customer //each customer Object
                                -> customer.getEmail()) // Stream<Object> i.e Stream<String>
                        .collect(Collectors.toList()) // List<Object> i.e List<String>
                ); // Mono<Object> i.e Mono<List<String>>

        emails
                .doFirst(()-> {
                    System.out.println();
                    System.out.println("\n Reactive Map : ");
                })
                .subscribe(System.out::println,
                        error -> System.out.println("Error Occured"),
                        System.out::println);
    }

    /**
     * Map Vs FlatMap in Functional Programming
     */
    public static void mapVsFlatMap_Functional() {

        List<Customer> customers = DataBase.getAll().block();

        List<List<String>> phoneNumbers = customers.  //List<Customer>
                stream() //Stream<Customer>
                .map(customer -> customer.getPhoneNumbers()) //Stream<List<String>>
                .collect(Collectors.toList()); //List<List<String>>

        List<String> phones = customers //List<Customer>
                .stream() //Stream<Customer>
                .flatMap(customer -> customer.getPhoneNumbers().stream()) //Stream<String>
                .collect(Collectors.toList()); //List<String>

        System.out.println("\n Functional Map vs Flatmap : \n");
        System.out.println("Map : "+ phoneNumbers);
        System.out.println("Flatmap : "+ phones);

    }

    /**
     * Map Vs FlatMap in Reactive Programming
     */
    public static void mapVsFlatMap_Reactive() {

        Mono<List<Customer>> customers = DataBase.getAll();

        Mono<List<List<String>>> phoneNumbers = customers //Mono<List<Customer>>
                .map(customers1 // List<Customer>
                        -> customers1.stream() // Stream<Customer>
                        .map(customer //each customer Object
                                -> customer.getPhoneNumbers()) // Stream<Object> i.e Stream<List<String>>
                        .collect(Collectors.toList()) // List<List<Object>> i.e List<List<String>>
                ); // Mono<Object> i.e Mono<List<List<String>>>

        System.out.println("\n Reactive Map vs Flatmap : \n");

        phoneNumbers
                .doFirst(()-> System.out.print("Map : "))
                .subscribe(System.out::println);

        Mono<List<String>> phones = customers //Mono<List<Customer>>
                .map(customers1 // List<Customer>
                        -> customers1.stream() // Stream<Customer>
                        .flatMap(customer //each customer Object
                                -> customer.getPhoneNumbers().stream()) // Stream<Object> i.e Stream<String>
                        .collect(Collectors.toList()) // List<List<Object>> i.e List<String>
                ); // Mono<Object> i.e Mono<List<String>>

        phones
                .doFirst(()-> System.out.print("Flatmap : "))
                .subscribe(System.out::println);
    }

    public static void divider() {
        IntStream.range(1,200).forEach(c-> System.out.print("-"));
    }

    public static void main(String[] args) {
        map_Reactive();
        divider();
        map_Functional();
        divider();
        mapVsFlatMap_Functional();
        divider();
        mapVsFlatMap_Reactive();

    }
}

