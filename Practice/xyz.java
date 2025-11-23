//override and extensds 

public class xyz {
    abstract static class Animal {
        abstract void sound();
    }

    static class Dog extends Animal {
        @Override  // used for overwrite the class parent class to sub class
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
        a = new Dog();
        a.sound();   // Dog barks (runtime decision)

        a = new Cat();
        a.sound();   // Cat meows
    }
}
