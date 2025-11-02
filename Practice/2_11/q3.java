public class q3 {
    interface A{
        void show(
        );
    }
    interface B{
        void print();
    }
    static class Child implements A,B{
        public void show(){
            System.out.println("Show Methods from A  !! ");
        }
        public void print(){
            System.out.println("Proint Methods from B !! ");
        }

    }
    public static void main(String[] args) {
            Child obj=new Child();
            obj.show();
            obj.print();

    }
}
