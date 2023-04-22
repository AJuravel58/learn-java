package org.example.stream1Create;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamApp1Create {
    public static void main(String[] args) {

        long count = IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5).filter(w -> w > 0).count();
        System.out.println(count);

        ArrayList<String> cities = new ArrayList<String>();
        Collections.addAll(cities, "Париж", "Лондон", "Мадрид");
        cities.stream() // получаем поток
                .filter(s -> s.length() == 6) // применяем фильтрацию по длине строки
                .forEach(s -> System.out.println(s)); // выводим отфильтрованные строки на консоль

        Stream<String> citiesStream = cities.stream(); // получаем поток
        citiesStream = citiesStream.filter(s -> s.length() == 6); // применяем фильтрацию по длине строки
        citiesStream.forEach(s -> System.out.println(s)); // выводим отфильтрованные строки на консоль

        Stream<String> citiesStream2 = Arrays.stream(new String[]{"Париж", "Лондон", "Мадрид"}) ;
        citiesStream2.forEach(s->System.out.println(s)); // выводим все элементы массива

        Stream<String> citiesStream3 =Stream.of("Париж", "Лондон", "Мадрид");
        citiesStream3.forEach(System.out::println);

        IntStream intStream = Arrays.stream(new int[]{1,2,4,5,7});
        intStream.forEach(i->System.out.println(i));

        LongStream longStream = Arrays.stream(new long[]{100,250,400,5843787,237});
        longStream.forEach(l->System.out.println(l));

        DoubleStream doubleStream = Arrays.stream(new double[] {3.4, 6.7, 9.5, 8.2345, 121});
        //doubleStream.forEach(d->System.out.println(d));
        doubleStream.forEach(System.out::println);


    }
}
