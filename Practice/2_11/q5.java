public class q5 {
    public static class student{
        int id;
        StringBuilder name;
        student(int id,StringBuilder name){
            this.id=id;
            this.name=new StringBuilder(name);
        }
        student(student s){
            id=s.id;
            name=new StringBuilder(s.name.toString());
        }
    }
    public static void main(String[] args) {
        student s=new student(101,new StringBuilder("Subham"));
        System.out.println(s.name);
        student s2=new student(s);
        s2.name.append(" das");
        System.out.println(s2.name);
        System.out.println(s.name);

    }
}
