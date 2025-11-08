class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread is running: " + i);
            try {
                Thread.sleep(500); // sleep for 0.5 sec to show execution clearly
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class t1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();  // create thread object
        t1.start();                    // start thread execution

        // main thread work
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}
