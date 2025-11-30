public class q1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Multithreading Demo Running...");
        Thread t1 = new MyThread();
        t1.start();
        Thread t2 = new Thread(new MyRunnable());
        t2.start();
        java.util.concurrent.ExecutorService es =java.util.concurrent.Executors.newFixedThreadPool(2);
        es.submit(() -> System.out.println("Executor Task 1"));
        es.submit(() -> System.out.println("Executor Task 2"));
        es.shutdown();
    }
}
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread from MyThread running");
    }
}
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread from MyRunnable running");
    }
}

