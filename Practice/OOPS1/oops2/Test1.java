package OOPS1.oops2;

class A {
    A() {
        System.out.println("A");
    }
}

class B extends A {
    B() {
        this(10);
        System.out.println("B");
    }
    B(int x) {
        System.out.println("B with int");
    }
}

public class Test1 {
    public static void main(String[] args) {
        new B();
    }
}

