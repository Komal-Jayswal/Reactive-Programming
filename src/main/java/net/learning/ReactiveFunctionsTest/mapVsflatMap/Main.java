package net.learning.ReactiveFunctionsTest.mapVsflatMap;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

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
     * Map in Reactive Programming with mono
     */
    public static void map_Reactive(){

        Mono<List<Customer>> customers = DataBase.getAll();

        Mono<List<String>> emails = customers //Mono<List<Customer>>
                .map(customers1 // List<Customer>
                        -> //each customer Object
                        customers1.stream() // Stream<Customer>
                        .map(Customer::getEmail) // Stream<Object> i.e Stream<String>
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
                .map(customer -> customer.getPhoneNumbers())
                .toList(); //List<List<String>>

        List<String> phones = customers //List<Customer>
                .stream() //Stream<Customer>
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .toList(); //List<String>

        System.out.println("\n Functional Map vs Flatmap : \n");
        System.out.println("Map : "+ phoneNumbers);
        System.out.println("Flatmap : "+ phones);

    }

    /**
     * Map Vs FlatMap in Reactive Programming with mono
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

    /**
     * Map Vs FlatMap in Reactive Programming with flux
     */
    public static void mapVsFlatMapWithFluxInReactive() {

        List<Customer> customerList = new ArrayList<>();

        customerList.add(new Customer(101, "john", "john@gmail.com", Arrays.asList("397937955", "21654725")));
        customerList.add(new Customer(102, "smith", "smith@gmail.com", Arrays.asList("89563865", "2487238947")));
        customerList.add(new Customer(103, "peter", "peter@gmail.com", Arrays.asList("38946328654", "3286487236")));
        customerList.add(new Customer(104, "kely", "kely@gmail.com", Arrays.asList("389246829364", "948609467")));

        Flux<Customer> customers = Flux.fromIterable(customerList);

        customers.doFirst(()-> System.out.println("\n Customers Details From Database in form of flux \n"))
                .subscribe(System.out::println);

        Mono<List<String>> emailMono = customers
                .map(Customer::getEmail)
                .collect(Collectors.toList());

        emailMono.doFirst(()-> System.out.println("\n\n Map : Collect all customer's email in form of Mono<List<String>> \n"))
                .subscribe(System.out::println);

        Flux<String> emailFlux = customers
                .map(Customer::getEmail);

        emailFlux.doFirst(()-> System.out.println("\n\n Map : Collect all customer's email in form of Flux<String> \n"))
                .subscribe(System.out::println);

        Mono<List<List<String>>> phoneMono = customers
                .map(Customer::getPhoneNumbers)
                .collect(Collectors.toList());

        phoneMono.doFirst(()-> System.out.println("\n\n Map : Collect all customer's Phone Numbers in form of Mono<List<List<String>>> \n"))
                .subscribe(System.out::println);

        Flux<List<String>> phoneFlux = customers
                .map(Customer::getPhoneNumbers);

        phoneFlux.doFirst(()-> System.out.println("\n\n Map : Collect all customer's Phone Numbers in form of Flux<List<String>> \n"))
                .subscribe(System.out::println);

        Flux<String> phoneFlux_FlatMap = Flux.fromIterable(customers
                .toStream() //Stream<customer>
                .flatMap(customer -> customer.getPhoneNumbers().stream()) //Stream<String>
                .collect(Collectors.toList())); //Flux<String>

        phoneFlux_FlatMap.doFirst(()-> System.out.println("\n\n FlatMap : Collect all customer's Phone Numbers in form of Flux<String> \n"))
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
        divider();
        mapVsFlatMapWithFluxInReactive();

    }
}

