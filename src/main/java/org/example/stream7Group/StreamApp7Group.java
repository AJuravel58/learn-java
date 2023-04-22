package org.example.stream7Group;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp7Group {
    public static void main(String[] args) {

        List<Phone> listPhones = List.of(
                new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));

        // - Collectors.groupingBy() - Чтобы сгруппировать данные по какому-нибудь признаку, нам надо использовать в связке метод collect() объекта Stream и метод Collectors.groupingBy()
        Map<String, List<Phone>> mapByCompany = listPhones.stream().collect(Collectors.groupingBy(Phone::getCompany));
        for (Map.Entry<String, List<Phone>> item : mapByCompany.entrySet()) {
            System.out.println(item);
        }

        // Collectors.partitioningBy() имеет похожее действие, только он делит элементы на группы по принципу, соответствует ли элемент определенному условию:
        Map<Boolean, List<Phone>> mapByCompany2 = listPhones.stream().collect(Collectors.partitioningBy(p -> p.getCompany() == "Apple"));
        for (Map.Entry<Boolean, List<Phone>> item : mapByCompany2.entrySet()) {
            System.out.println(item);
        }

        // - Collectors.counting применяется в Collectors.groupingBy() для вычисления количества элементов в каждой группе:
        Map<String, Long> mapByCompany3 = listPhones.stream().collect(Collectors.groupingBy(Phone::getCompany, Collectors.counting()));
        for (Map.Entry<String, Long> item : mapByCompany3.entrySet()) {
            System.out.println(item);
        }

        // - Collectors.summing применяется для подсчета суммы. В зависимости от типа данных, к которым применяется метод, он имеет следующие формы: summingInt(), summingLong(), summingDouble().
        Map<String, Integer> mapSumByCompany = listPhones.stream().collect(Collectors.groupingBy(Phone::getCompany, Collectors.summingInt(Phone::getPrice)));
        for (Map.Entry<String, Integer> item : mapSumByCompany.entrySet()) {
            System.out.println(item);
        }

        // - maxBy и minBy применяются для подсчета минимального и максимального значения в каждой группе. В качестве параметра эти методы принимают функцию компаратора, которая нужна для сравнения значений.
        Map<String, Optional<Phone>> phonesByCompany = listPhones.stream().collect(Collectors.groupingBy(Phone::getCompany, Collectors.minBy(Comparator.comparing(Phone::getPrice))));
        for (Map.Entry<String, Optional<Phone>> item : phonesByCompany.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue().get().getName());
        }

        // - summarizingInt() / summarizingLong() / summarizingDouble() позволяют объединить в набор значения соответствующих типов:
        // Данный набор инкапсулируется в объекте IntSummaryStatistics. Соответственно если бы мы применяли методы summarizingLong() или summarizingDouble(),
        // то соответственно бы получали объекты LongSummaryStatistics или DoubleSummaryStatistics.
        // У этих объектов есть ряд методов, который позволяют выполнить различные атомарные операции над набором:
        // getAverage(): возвращает среднее значение
        // getCount(): возвращает количество элементов в наборе
        // getMax(): возвращает максимальное значение
        // getMin(): возвращает минимальное значение
        // getSum(): возвращает сумму элементов
        // accept(): добавляет в набор новый элемент
        Map<String, IntSummaryStatistics> mapPriceSummary = listPhones.stream().collect(Collectors.groupingBy(Phone::getCompany, Collectors.summarizingInt(Phone::getPrice)));
        for (Map.Entry<String, IntSummaryStatistics> item : mapPriceSummary.entrySet()) {
            System.out.println(item.getKey() + " Avarge = " + item.getValue().getAverage() + " Sum = " + item.getValue().getSum());
        }

        // - mapping позволяет дополнительно обработать данные и задать функцию отображения объектов из потока на какой-нибудь другой тип данных.
        Map<String, List<String>> mapPhonesByCompany = listPhones.stream().collect(Collectors.groupingBy(Phone::getCompany, Collectors.mapping(Phone::getName, Collectors.toList())));
        for(Map.Entry<String, List<String>> item : mapPhonesByCompany.entrySet()){
            System.out.println(item);
        }

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

    public int getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "name=" + name + " company=" + company + " price=" + price;
    }
}