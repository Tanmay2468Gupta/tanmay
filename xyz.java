

public class xyz {
    static class Animal {
        void sound() {
            System.out.println("Animal makes a sound");
        }
    }

    static class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("Dog barks");
        }
    }

    static class Cat extends Animal {
        @Override
        void sound() {
            System.out.println("Cat meows");
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
