public class q3 {
    static class Animal{
        void sound(){
            System.out.println("Animal can sound !!");
        }
    }
    static class Dog extends Animal{
        // @Override
        void sound(){
            // System.out.println("Dog can sound !! ");
            super.sound();
        }
    }
    public static void main(String[] args) {
        Animal d=new Dog();
        d.sound();
    }
}
