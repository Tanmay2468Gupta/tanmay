public class t2 {
    static class MyThread extends Thread {
    private static String name;
    MyThread(String name) {
        this.name = name; 
    }
    public void run() {
        for(int i=1; i<=3; i++) {
            System.out.println(name + " running: " + i);
            try { Thread.sleep(500); } catch(Exception e) {}
        }
    }
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");
        t1.start();
        t2.start();
    }
}

}
