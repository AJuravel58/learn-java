package org.example.lambda2Parameters;

public class LambdaApp2Parameters {
    public static void main(String[] args) {


        // КАК Параметры вызовов
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Expression func = (n) -> n % 2 == 0;
        System.out.println(sum(nums, func)); // 20
        System.out.println(sum(nums, (n) -> n % 4 == 0)); // 12

        int[] nums2 = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        System.out.println(sum(nums2, ExpressionHelper::isEven));
        Expression expr = ExpressionHelper::isPositive;
        System.out.println(sum(nums2, expr));

        ExpressionHelper2 expressionHelper2 = new ExpressionHelper2();
        System.out.println(sum(nums2, expressionHelper2::isEven));
        System.out.println(sum(nums2, expressionHelper2::isPositive));

        // КАК Конструктор
        UserBuilder userBuilder = User::new;
        User user = userBuilder.create("Вася");
        System.out.println(user.getName());

        // КАК результат
        Operation operation = action(1);
        int a = operation.execute(6, 5);
        System.out.println(a);          // 11

        int b = action(2).execute(8, 2);
        System.out.println(b);          // 6
    }

    private static int sum(int[] numbers, Expression func) {
        int result = 0;
        for (int i : numbers) {
            if (func.isEqual(i))
                result += i;
        }
        return result;
    }

    private static Operation action(int number) {
        switch (number) {
            case 1:
                return (x, y) -> x + y;
            case 2:
                return (x, y) -> x - y;
            case 3:
                return (x, y) -> x * y;
            default:
                return (x, y) -> 0;
        }
    }
}

class ExpressionHelper {
    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    static boolean isPositive(int n) {
        return n > 0;
    }
}

class ExpressionHelper2 {
    boolean isEven(int n) {
        return n % 2 == 0;
    }

    boolean isPositive(int n) {
        return n > 0;
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

@FunctionalInterface
interface UserBuilder {
    User create(String name);
}

@FunctionalInterface
interface Expression {
    boolean isEqual(int n);
}

@FunctionalInterface
interface Operation {
    int execute(int x, int y);
}