public class q4 {
    interface A{
        default void show(){
            System.out.println("A Show !!");
        }
    }
    interface B{
        default void show(){
            System.out.println("B Show !!");
        }
    }
    static class c implements A,B{
        public void show(){
            System.out.println("C override the Show value !! ");
        }
    }

    public static void main(String[] args) {
        c obj=new c();
        obj.show();
    }
}
