package org.example.any;

public class Main {
    public static void main(String[] args) {
        var ca = new ClassA();
        var cb = new ClassB();
        var cc = new ClassC();
        System.out.println("ca.a = " + ca.a);
        System.out.println("cb.a = " + cb.a + " cb.b = " + cb.b);
        System.out.println("cc.a = " + cc.a + " cc.b = " + cc.b + " cc.c = " + cc.c);
        System.out.println("-------------------------------------");
    }
}
