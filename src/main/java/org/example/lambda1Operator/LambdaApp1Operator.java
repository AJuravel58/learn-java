package org.example.lambda1Operator;

public class LambdaApp1Operator {
    public static void main(String[] args) {

        OperationableRetPrm2 opRetPrm2;
        opRetPrm2 = (x,y)->x+y;
        //opRetPrm2 = (x,y)->{return x+y;};
        //opRetPrm2 = (x,y)->{int z = x + y; return z;};
        int result2 = opRetPrm2.calculate(10, 20);
        System.out.println(result2); //30

        OperationableRetPrm1 op1;
        op1 = (x) -> x*x;
        int result1 = op1.calculate(11);
        System.out.println(result1); // 121

        OperationableRetPrm0 opRetPrm0;
        opRetPrm0 = () -> 123;
        int result0 = opRetPrm0.calculate();
        System.out.println(result0); // 123

        OperationableVoidPrm0 opVoidPrm0;
        opVoidPrm0 = () -> System.out.println("Void no Prm");
        opVoidPrm0.calculate();

        OperationableVoidPrm1 opVoidPrm1;
        opVoidPrm1 = (x) -> System.out.println(x);
        opVoidPrm1.calculate(10);

        OperationableType<Integer> opTypeI = ((x, y) -> x+y);
        int opResultTypeI = opTypeI.calculate(10, 100);
        System.out.println(opResultTypeI);
        OperationableType<
        String> opTypeS = ((s1, s2) -> s1+s2);
        String opResultTypeS = opTypeS.calculate("Str1", "Str2");
        System.out.println(opResultTypeS);

    }
}
@FunctionalInterface
interface OperationableRetPrm2{
    int calculate(int x, int y);
}
@FunctionalInterface
interface OperationableRetPrm1{
    int calculate(int x);
}
@FunctionalInterface
interface OperationableRetPrm0{
    int calculate();
}
@FunctionalInterface
interface OperationableVoidPrm0{
    void calculate();
}

@FunctionalInterface
interface OperationableVoidPrm1{
    void calculate(int x);
}
interface OperationableType<T>{
    T calculate(T x, T y);
}