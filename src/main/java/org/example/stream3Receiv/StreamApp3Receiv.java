package org.example.stream3Receiv;

import java.util.stream.Stream;

public class StreamApp3Receiv {
    public static void main(String[] args) {

        // Метод takeWhile() выбирает из потока элементы, пока они соответствуют условию. Если попадается элемент, который не соответствует условию, то метод завершает свою работу. Выбранные элементы возвращаются в виде потока.
        Stream<Integer> numbers = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers.takeWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));

        Stream<Integer> numbers2 = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers2.sorted()
                .takeWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));

        // Метод dropWhile() выполняет обратную задачу - он пропускает элементы потока, которые соответствуют условию до тех пор, пока не встретит элемент, который НЕ соответствует условию:
        Stream<Integer> numbers3 = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5, 99);
        numbers3.sorted()
                .dropWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));

        // Статический метод concat() объединяет элементы двух потоков, возвращая объединенный поток:
        Stream<String> people1 = Stream.of("Tom", "Bob", "Sam");
        Stream<String> people2 = Stream.of("Alice", "Kate", "Sam");
        Stream.concat(people1, people2)
                .forEach(n -> System.out.println(n));

        // Метод distinct() возвращает только ункальные элементы в виде потока:
        Stream<String> people = Stream.of("Tom", "Bob", "Sam", "Tom", "Alice", "Kate", "Sam");
        people.distinct()
                .forEach(p -> System.out.println(p));

        // Метод skip(long n) используется для пропуска n элементов. Этот метод возвращает новый поток, в котором пропущены первые n элементов.
        // Метод limit(long n) применяется для выборки первых n элементов потоков. Этот метод также возвращает модифицированный поток, в котором не более n элементов.
        Stream<String> phoneStream = Stream.of("iPhone 6 S", "Lumia 950", "Samsung Galaxy S 6", "LG G 4", "Nexus 7");
        phoneStream.skip(1)
                .limit(2)
                .forEach(System.out::println);

    }
}
