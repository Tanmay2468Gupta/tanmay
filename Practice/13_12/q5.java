public class q5 {
    
    public static  class Student {
        int id;
        String[] subjects;
    
        Student(int id, String[] subjects) {
            this.id = id;
            this.subjects = subjects.clone();
        }
    }
    public static void main(String[] args) {
        String[] subs = {"Math", "Physics"};
        Student s1 = new Student(101, subs);

        // Shallow copy
        Student s2 = new Student(s1.id, s1.subjects);

        s2.subjects[0] = "Chemistry";

        System.out.println(s1.subjects[0]); // Chemistry
        System.out.println(s2.subjects[0]); // Chemistry
    }
}

