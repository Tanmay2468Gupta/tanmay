public class abc {
    static class MyThread extends Thread {
        public void run() {
            System.out.println("Running in: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();
    }
}
