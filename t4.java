public class t4 {
    static class MyThread extends Thread {
        public void run() {
            System.out.println("Currently running thread: " + Thread.currentThread());
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("MyWorkerThread");
        t1.start();

        System.out.println("Currently running thread (main): " + Thread.currentThread());
    }
}
