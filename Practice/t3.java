public class t3 {
    static class MyThread extends Thread {
        public void run() {
            // Print current thread name
            System.out.println("Thread is running... " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("MyCustomThread"); // Optional — give it a name
        t1.start();

        // Print the name of the main thread
        System.out.println("Main thread name: " + Thread.currentThread().getName());
    }
}
