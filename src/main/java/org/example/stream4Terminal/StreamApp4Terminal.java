package org.example.stream4Terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class StreamApp4Terminal {
    public static void main(String[] args) {

        // Метод count() возвращает количество элементов в потоке данных:
        ArrayList<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

        System.out.println(names.stream().count());  // =4
        System.out.println(names.stream().filter(n -> n.length() <= 3).count());  // // количество элементов с длиной не больше 3 символов =3

        // Метод findFirst() извлекает из потока первый элемент, а findAny() извлекает случайный объект из потока (нередко так же первый):
        ArrayList<String> names2 = new ArrayList<String>();
        names2.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

        Optional<String> first = names2.stream().findFirst();
        System.out.println(first.get());    // Tom

        Optional<String> any = names2.stream().findAny();
        System.out.println(first.get());    // ?

        //boolean allMatch(Predicate<? super T> predicate): возвращает true, если все элементы потока удовлетворяют условию в предикате
        //boolean anyMatch(Predicate<? super T> predicate): возвращает true, если хоть один элемент потока удовлетворяют условию в предикате
        //boolean noneMatch(Predicate<? super T> predicate): возвращает true, если ни один из элементов в потоке не удовлетворяет условию в предикате
        ArrayList<String> names3 = new ArrayList<String>();
        names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

        // есть ли в потоке строка, длина которой больше 3
        boolean anye = names.stream().anyMatch(s -> s.length() > 3);
        System.out.println(anye);    // true

        // все ли строки имеют длину в 3 символа
        boolean all = names.stream().allMatch(s -> s.length() == 3);
        System.out.println(all);    // false

        // НЕТ ЛИ в потоке строки "Bill". Если нет, то true, если есть, то false
        boolean none = names.stream().noneMatch(s -> s == "Bill");
        System.out.println(none);   // true

        //Optional<T> min(Comparator<? super T> comparator)
        //Optional<T> max(Comparator<? super T> comparator)
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        System.out.println(min.get());  // 1
        System.out.println(max.get());  // 9

        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.addAll(Arrays.asList(new Phone[]{
                new Phone("iPhone 8", 52000),
                new Phone("Nokia 9", 35000),
                new Phone("Samsung Galaxy S9", 48000),
                new Phone("HTC U12", 36000)
        }));
        Phone minPrice = phones.stream().min(Phone::compare).get();
        Phone maxPrice = phones.stream().max(Phone::compare).get();
        System.out.printf("MIN Name: %s Price: %d \n", minPrice.getName(), minPrice.getPrice());
        System.out.printf("MAX Name: %s Price: %d \n", maxPrice.getName(), maxPrice.getPrice());


    }
}

class Phone {

    private String name;
    private int price;

    public Phone(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static int compare(Phone p1, Phone p2) {
        if (p1.getPrice() > p2.getPrice())
            return 1;
        return -1;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}