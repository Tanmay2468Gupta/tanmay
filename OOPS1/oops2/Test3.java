package OOPS1.oops2;

class A {
    void show() { System.out.println("A show"); }
}

class B extends A {
    void show() { System.out.println("B show"); }
}

public class Test3 {
    public static void main(String[] args) {
        A obj = new B();
        obj.show();
    }
}

