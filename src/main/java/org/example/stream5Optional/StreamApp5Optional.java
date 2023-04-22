package org.example.stream5Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class StreamApp5Optional {
    public static void main(String[] args) {
        // Ряд операций сведения, такие как min, max, reduce, возвращают объект Optional<T>
        //После выполнения операции с помощью метода get() объекта Optional мы можем получить его значение:
        //Но что, если поток не содержит вообще никаких данных: В этом случае программа выдаст исключение java.util.NoSuchElementException

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        Optional<Integer> minOpt = numbers.stream().min(Integer::compare);
        System.out.println(minOpt.get());  // 1

        // isPresent(). Он возврашает true, если значение присутствует в Optional, и false, если значение отсутствует:
        ArrayList<Integer> numbers2 = new ArrayList<Integer>();
        Optional<Integer> minOpt2 = numbers2.stream().min(Integer::compare);
        if (minOpt2.isPresent()) {
            System.out.println("val = " + minOpt2.get());
        } else {
            System.out.println("val = not present");
        }

        // orElse() позволяет определить альтернативное значение, которое будет возвращаться, если Optional не получит из потока какого-нибудь значения:
        // пустой список
        ArrayList<Integer> numbers3 = new ArrayList<Integer>();
        Optional<Integer> minOpt3 = numbers3.stream().min(Integer::compare);
        System.out.println(minOpt3.orElse(-1)); // -1
        // непустой список
        numbers3.addAll(Arrays.asList(new Integer[]{4, 5, 6, 7, 8, 9}));
        minOpt3 = numbers3.stream().min(Integer::compare);
        System.out.println(minOpt3.orElse(-1)); // 4

        // orElseGet() позволяет задать функцию, которая будет возвращать значение по умолчанию:
        // пустой список
        ArrayList<Integer> numbers4 = new ArrayList<Integer>();
        Optional<Integer> minOpt4 = numbers4.stream().min(Integer::compare);
        System.out.println(minOpt4.orElseGet(() -> new Random().nextInt(100)));
        // непустой список
        numbers4.addAll(Arrays.asList(new Integer[]{4, 5, 6, 7, 8, 9}));
        minOpt4 = numbers4.stream().min(Integer::compare);
        System.out.println(minOpt4.orElse(-1)); // 4

        // orElseThrow позволяет сгенерировать исключение, если Optional не содержит значения:
        ArrayList<Integer> numbers5 = new ArrayList<Integer>();
        Optional<Integer> minOpt5 = numbers5.stream().min(Integer::compare);
        // генеррация исключения IllegalStateException
        //System.out.println(minOpt5.orElseThrow(IllegalStateException::new));

        // ifPresent() определяет действия со значением в Optional, если значение имеется:
        ArrayList<Integer> numbers6 = new ArrayList<Integer>();
        numbers6.addAll(Arrays.asList(new Integer[]{4, 5, 6, 7, 8, 9}));
        Optional<Integer> minOpt6 = numbers6.stream().min(Integer::compare);
        minOpt6.ifPresent(v -> System.out.println(v)); // 4

        // ifPresentOrElse() позволяет определить альтернативную логику на случай, если значение в Optional отсутствует:
        // пустой список
        ArrayList<Integer> numbers7 = new ArrayList<Integer>();
        Optional<Integer> minOpt7 = numbers7.stream().min(Integer::compare);
        minOpt7.ifPresentOrElse(
                v -> System.out.println(v),
                () -> System.out.println("Value not found")
        );
        // непустой список
        numbers7.addAll(Arrays.asList(new Integer[]{4, 5, 6, 7, 8, 9}));
        minOpt7 = numbers7.stream().min(Integer::compare);
        minOpt7.ifPresentOrElse(
                v -> System.out.println(v),
                () -> System.out.println("Value not found")
        );



    }


}

