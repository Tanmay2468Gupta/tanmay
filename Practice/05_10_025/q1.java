public class q1 {
    public static class A {
        A() {
            System.out.println("A constructor");
        }
    }

    static class B extends A {
        B() {
            System.out.println("B constructor");
        }
    }

    public static void main(String[] args) {
        B obj = new B();
    }
}
