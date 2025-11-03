public class q2 {
    interface A{
        void showA();
    }
    interface B extends A{
         void showB();
    }
    static class c implements B{
        public void showA(){
            System.out.println("A !!");
        }
        public void showB(){
            System.out.println("B !! ");
        }
    }
    public static void main(String[] args) {
        c obj=new c();
        obj.showA();
        obj.showB();
    }
}
