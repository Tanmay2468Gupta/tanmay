public class q1 {
    public static class A {
        A() {
            System.out.println("A constructor");
        }
        static void show(){
            System.out.println("A show !!");
        }
    }

    static class B extends A {
        B() {
            System.out.println("B constructor");
        }
        static void show(){
            System.out.println("B show !!");
        }
    }

    public static void main(String[] args) {
        A obj = new B();
        // B obj =new B();
        obj.show();
    }
}
