package OOPS1.oops2;

class A {
    static void show() { System.out.println("Static A"); }
}

class B extends A {
    static void show() { System.out.println("Static B"); }
}

public class Test4 {  // Static methods are not overridden, they are hidden.
    public static void main(String[] args) {
        A obj = new B();
        obj.show();
    }
}
