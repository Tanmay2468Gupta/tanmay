public class q1 {
    public static class Animal{
        void eat(){
            System.out.println("Animal can eat !!");
        }
    }
    public static class Dog extends Animal{
        void bark(){
            System.out.println("Barking !!");
        }
    }
    public static class puppy extends Dog{
        void weep(){
            System.out.println("Weeping !!");
        }
    }
    public static void main(String[] args) {
        // Dog d=new Dog();
        // d.eat();
        // d.bark();
        puppy p= new puppy();
        p.eat();
        p.bark();
        p.weep();
    }
}
