package org.example.lambda3Embed;

import java.util.Scanner;
import java.util.function.*;

public class LambdaApp3Embed {

    public static void main(String[] args) {

        // Функциональный интерфейс Predicate<T> проверяет соблюдение некоторого условия. Если оно соблюдается, то возвращается значение true. В качестве параметра лямбда-выражение принимает объект типа T:
        //public interface Predicate<T> {
        //    boolean test(T t);
        //}
        Predicate<Integer> predicateOp = x -> x > 0;
        System.out.println(predicateOp.test(5)); // true
        System.out.println(predicateOp.test(-7)); // false

        // BinaryOperator<T> принимает в качестве параметра два объекта типа T, выполняет над ними бинарную операцию и возвращает ее результат также в виде объекта типа T:
        //public interface BinaryOperator<T> {
        //    T apply(T t1, T t2);
        //}
        BinaryOperator<Integer> binaryOp = (x, y) -> x * y;
        System.out.println(binaryOp.apply(3, 5)); // 15
        System.out.println(binaryOp.apply(10, -2)); // -20

        // UnaryOperator<T> принимает в качестве параметра объект типа T, выполняет над ними операции и возвращает результат операций в виде объекта типа T:
        //public interface UnaryOperator<T> {
        //    T apply(T t);
        //}
        UnaryOperator<Integer> unaryOp = x -> x * x;
        System.out.println(unaryOp.apply(5)); // 25

        // Функциональный интерфейс Function<T,R> представляет функцию перехода от объекта типа T к объекту типа R:
        //public interface Function<T, R> {
        //    R apply(T t);
        //}
        Function<Integer, String> convert = x -> String.valueOf(x) + " БАКСОВ";
        System.out.println(convert.apply(5)); // 5 долларов

        // Consumer<T> выполняет некоторое действие над объектом типа T, при этом ничего не возвращая:
        Consumer<Integer> consumerOp = x -> System.out.printf("%d Баксов \n", x);
        consumerOp.accept(800); // 800 долларов

        // Supplier<T> не принимает никаких аргументов, но должен возвращать объект типа T:
        Supplier<User> supplierOp = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя: ");
            String name = in.nextLine();
            return new User(name);
        };
        User user1 = supplierOp.get();
        User user2 = supplierOp.get();
        System.out.println("Имя user1: " + user1.getName());
        System.out.println("Имя user2: " + user2.getName());
    }

}

class User {
    private String name;

    String getName() {
        return name;
    }

    User(String n) {
        this.name = n;
    }
}