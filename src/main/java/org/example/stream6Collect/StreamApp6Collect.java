package org.example.stream6Collect;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp6Collect {
    public static void main(String[] args) {

        // ФОРМАТ-1: <R,A> R collect(Collector<? super T,A,R> collector)
        // Параметр R представляет тип результата метода, параметр Т - тип элемента в потоке, а параметр А - тип промежуточных накапливаемых данных.
        // В итоге параметр collector представляет функцию преобразования потока в коллекцию.

        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones,
                "iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        // toList(): преобразование к типу List
        List<String> listPhones = phones.stream().filter(s -> s.length() < 10)
                .collect(Collectors.toList());
        System.out.println(listPhones);

        // toSet(): преобразование к типу Set
        Set<String> setPhones = phones.stream().filter(s -> s.length() < 10)
                .collect(Collectors.toSet());
        System.out.println(setPhones);

        HashSet<String> hasSetPhones = phones.stream().filter(s -> s.length() < 12).
                collect(Collectors.toCollection(HashSet::new));
        hasSetPhones.forEach(s -> System.out.println(s));

        // toMap(): преобразование к типу Map
        Stream<Phone> phoneStream = Stream.of(
                new Phone("iPhone 8", 54000),
                new Phone("Nokia 9", 45000),
                new Phone("Samsung Galaxy S9", 40000),
                new Phone("LG G6", 32000));

        Map<String, Integer> mapPhones = phoneStream.collect(Collectors.toMap(p -> p.getName(), t -> t.getPrice()));
        mapPhones.forEach((k, v) -> System.out.println(k + " = " + v));

        // ФОРМАТ-2: <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
        // supplier: создает объект коллекции
        // accumulator: добавляет элемент в коллекцию
        // combiner: бинарная функция, которая объединяет два объекта

        ArrayList<String> filteredPhones = phones.stream().filter(s -> s.length() < 12)
                .collect(
                        () -> new ArrayList<String>(), // создаем ArrayList
                        (list, item) -> list.add(item), // добавляем в список элемент
                        (list1, list2) -> list1.addAll(list2)); // добавляем в список другой список
        filteredPhones.forEach(s -> System.out.println(s));

    }
}

class Phone {

    private String name;
    private int price;

    public Phone(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}