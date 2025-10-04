package OOPS1.Inheri;

public class Car {
    private int fuel;  // private ensures that fuel can't be accessed directly from outside the class

    public Car() {
        this.fuel = 100;  // Initialize with 100 units of fuel
    }

    // Getter method for fuel
    public int getFuel() {
        return fuel;
    }

    // Setter method for fuel with encapsulation enforcing constraints
    public void setFuel(int fuel) {
        if (fuel >= 0 && fuel <= 100) {
            this.fuel = fuel;
        } else {
            System.out.println("Invalid fuel amount.");
        }
    }

    public void drive() {
        if (fuel > 0) {
            fuel--;
            System.out.println("Vroom!");
        } else {
            System.out.println("Out of fuel!");
        }
    }

    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.drive();
        System.out.println("Fuel remaining: " + myCar.getFuel());
        myCar.setFuel(120);  // This will print "Invalid fuel amount."
    }
}