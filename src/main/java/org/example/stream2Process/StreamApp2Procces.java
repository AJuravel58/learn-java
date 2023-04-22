package org.example.stream2Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamApp2Procces {

    public static void main(String[] args) {
        Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид", "Берлин", "Брюссель");

        //Перебор элементов. Метод forEach принимает объект Consumer<? super String>
        citiesStream.forEach(System.out::println);

        //Фильтрация. Метод filter Он принимает в качестве параметра некоторое условие в виде объекта Predicate<T> и возвращает новый поток из элементов, которые удовлетворяют этому условию:
        Stream<String> citiesStream2 = Stream.of("Париж", "Лондон", "Мадрид", "Берлин", "Брюссель");
        citiesStream2.filter(s -> s.length() == 6).forEach(s -> System.out.println(s));

        Stream<Phone> phoneStream = Stream.of(
                new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));
        phoneStream.filter(p -> p.getPrice() < 50000).forEach(p -> System.out.println(p.getName()));

        //Отображение. Передаваемая в метод map функция задает преобразование от объектов типа T к типу R. И в результате возвращается новый поток с преобразованными объектами.
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper)
        Stream<Phone> phoneStream2 = Stream.of(
                new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));
        phoneStream2.map(p -> p.getName()) // помещаем в поток только названия телефонов
                .forEach(s -> System.out.println(s));

        Stream<Phone> phoneStream3 = Stream.of(
                new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));
        phoneStream3.map(p -> "название: " + p.getName() + " цена: " + p.getPrice())
                .forEach(s -> System.out.println(s));

        // Плоское отображение. Плоское отображение выполняется тогда, когда из одного элемента нужно получить несколько
        // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        Stream<Phone> phoneStream4 = Stream.of(
                new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

        phoneStream4.flatMap(p -> Stream.of(
                        String.format("название: %s  цена без скидки: %d", p.getName(), p.getPrice()),
                        String.format("название: %s  цена со скидкой: %d", p.getName(), p.getPrice() - (int) (p.getPrice() * 0.1))
                ))
                .forEach(s -> System.out.println(s));

        // Сортировка
        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones,
                "iPhone X",
                "Nokia 9",
                "Huawei Nexus 6P",
                "Samsung Galaxy S8",
                "LG G6",
                "Xiaomi MI6",
                "ASUS Zenned 3",
                "Sony Xperia Z5",
                "Mizue Pro 6",
                "Pixel 2");
        phones.stream().filter(p -> p.length() < 12)
                .sorted() // сортировка по возрастанию
                .forEach(s -> System.out.println(s));

        Stream<Phone2> phones2 = Stream.of(
                new Phone2("iPhone X", "Apple", 600),
                new Phone2("Pixel 2", "Google", 500),
                new Phone2("iPhone 8", "Apple", 450),
                new Phone2("Nokia 9", "HMD Global", 150),
                new Phone2("Galaxy S9", "Samsung", 300));
        phones2.sorted(new Phone2Comparator())
                .forEach(p -> System.out.printf("%s (%s) - %d \n", p.getName(), p.getCompany(), p.getPrice()));


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

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class Phone2 {

    private String name;
    private String company;
    private int price;

    public Phone2(String name, String comp, int price) {
        this.name = name;
        this.company = comp;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }
}

class Phone2Comparator implements Comparator<Phone2> {

    public int compare(Phone2 a, Phone2 b) {

        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}