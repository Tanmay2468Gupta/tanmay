package OOPS1.Inheri;
abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Abstract method
    public abstract void makeSound();

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}



// Derived class Animal
class Lion extends Animal {
    public Lion(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Roar!");
    }
}

// Derived class
class Parrot extends Animal {
    private String color;

    public Parrot(String name, String color) {
        super(name);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void makeSound() {
        System.out.println("Squawk!");
    }
}

public class Zoo {
    public static void main(String[] args) {
        Lion simba = new Lion("Simba");
        Parrot rio = new Parrot("Rio", "Blue");

        System.out.println(simba.getName() + " is a Lion.");
        simba.makeSound();
        simba.sleep();

        System.out.println(rio.getName() + " is a " + rio.getColor() + " Parrot.");
        rio.makeSound();
        rio.sleep();
    }
}
