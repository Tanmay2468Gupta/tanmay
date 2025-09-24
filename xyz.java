

public class xyz {
    static class Animal {
        void sound() {
            System.out.println("Animal Can eat !! ");
        }
    }

    static class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("Dog eat");
        }
    }

    static class Cat extends Animal {
        @Override
        void sound() {
            System.out.println("Cat eat");
        }
    }
    public static void main(String[] args) {
        Animal a;  // reference of parent
        a = new Animal();
        a.sound();   // Dog barks (runtime decision)

        a = new Cat();
        a.sound();   // Cat meows
    }
}
