package org.example.stream8Parallel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamApp8Parallel {
    public static void main(String[] args) {

        Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6);
        Optional<Integer> result = numbersStream.parallel().reduce((x, y) -> x * y);
        System.out.println(result.get()); // 720

        List<String> people = Arrays.asList("Tom", "Bob", "Sam", "Kate", "Tim");
        System.out.println("Последовательный поток");
        people.stream().filter(p -> p.length() == 3).forEach(System.out::println);
        // В случае с параллельным потоком вывод недетерминирован и может отличаться.
        System.out.println("\nПараллельный поток");
        people.parallelStream().filter(p -> p.length() == 3).forEach(System.out::println);
        // Однако не все функции можно без ущерба для точности вычисления перенести с последовательных потоков на параллельные.
        // Прежде всего такие функции должны быть без сохранения состояния и ассоциативными,
        // то есть при выполнении слева направо давать тот же результат,
        // что и при выполнении справа налево, как в случае с произведением чисел.

        // Упорядоченность в параллельных потоках - как правило, элементы передаются в поток в том же порядке, в котором они определены в источнике данных.
        // При работе с параллельными потоками система сохраняет порядок следования элементов.
        // Исключение составляет метод forEach(), который может выводить элементы в произвольном порядке.
        // И чтобы сохранить порядок следования, необходимо применять метод forEachOrdered:
        List<Phone> listPhones = List.of(
                new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));
        listPhones.parallelStream()
                .sorted(Comparator.comparing(Phone::getName))
                .unordered()
                .forEach(s -> System.out.println(s));
        listPhones.parallelStream()
                .sorted(Comparator.comparing(Phone::getName))
                .forEachOrdered(System.out::println);


        //  Класс Arrays было добавлено ряд методов, которые позволяют в параллельном режиме совершать обработку элементов массива.
        //  И хотя данные методы формально не входят в Stream API, но реализуют схожую функциональность, что и параллельные потоки:
        // parallelPrefix(): вычисляет некоторое значение для элементов массива (например, сумму элементов)
        // parallelSetAll(): устанавливает элементы массива с помощью лямбда-выражения
        // parallelSort(): сортирует массив

        // parallelSetAll()
        Phone[] phones = new Phone[]{new Phone("iPhone 8", "C1", 54000),
                new Phone("Pixel 2", "C2", 45000),
                new Phone("Samsung Galaxy S9", "C3", 40000),
                new Phone("Nokia 9", "C4", 32000)};
        Arrays.stream(phones).forEachOrdered(System.out::println);
        Arrays.parallelSetAll(phones, i -> {
            phones[i].setPrice(phones[i].getPrice() - 10000);
            return phones[i];
        });
        Arrays.stream(phones).forEachOrdered(System.out::println);

        // parallelSort()
        int[] nums = {30, -4, 5, 29, 7, -8};
        Arrays.parallelSort(nums);
        Arrays.stream(nums).forEach(System.out::println);

        Phone[] dimPhones = new Phone[]{
                new Phone("iPhone 8", "C1", 54000),
                new Phone("Pixel 2", "C2", 45000),
                new Phone("Samsung Galaxy S9", "C3", 40000),
                new Phone("Nokia 9", "C4", 32000)};
        Arrays.parallelSort(dimPhones,new PhoneComparator());
        Arrays.stream(dimPhones).forEach(System.out::println);

        // parallelPrefix()
        // походит для тех случаев, когда надо получить элемент массива или объект того же типа, что и элементы массива, который обладает некоторыми признаками.
        // Например, в массиве чисел это может быть максимальное, минимальное значения и т.д. Например, найдем произведение чисел:
        // То есть, как мы видим, лямбда-выражение из Arrays.parallelPrefix, которое представляет бинарную функцию, получает два элемента и выполняет над ними операцию.
        // Результат операции сохраняется и передается в следующий вызов бинарной функции.
        int[] numbers = {1, 2, 3, 4, 5, 6};
        Arrays.parallelPrefix(numbers, (x, y) -> x * y);
        Arrays.stream(numbers).forEach(System.out::println);
    }
}

class Phone {

    private String name;
    private String company;
    private int price;

    public Phone(String name, String comp, int price) {
        this.name = name;
        this.company = comp;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "name=" + name + " company=" + company + " price=" + price;
    }
}

class PhoneComparator implements Comparator<Phone> {
    public int compare(Phone a, Phone b) {
        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}