import java.util.Timer;
import java.util.TimerTask;

/**
 * BucketThreadTester explores MultiThreads programming and race condition
 */

public class BucketThreadTester {
    static Thread th1;
    static Thread th2;

    public static void main(String[] args) {
        Bucket bucket = new Bucket();
        Producer producer = new Producer(bucket);
        Consumer consumer = new Consumer(bucket);
        th1 = new Thread(producer);
        th2 = new Thread(consumer);
        new BucketTimer().schedule();
    }


}

/**
 * BucketTimer calls the timers to start the threads (task) and stop them (task2)
 */
class BucketTimer {
    private Timer timer;
    private int count =0;
    private MyStop task2;
    void schedule(){
        TimerTask  task = new MySchedule();
        task2 = new MyStop();
        timer = new Timer();
        timer.schedule(task2,500, 100);
        timer.schedule(task,100,1000);

    }

    /**
     * MySchedule is task1, starts the threads and then puts them to sleep while catching the exceptions
     */
    class MySchedule extends TimerTask{
        public void run() {
            try {
                BucketThreadTester.th1.start();
                BucketThreadTester.th2.start();
                BucketThreadTester.th1.sleep(5000);
                BucketThreadTester.th2.sleep(5000);
            }catch(InterruptedException | RuntimeException e) {
                System.out.println("Thread Interrupted.");
                timer.cancel();
            }
        }
    }
    /**
     * MyStop is task2, interrupts the threads while they're sleeping
     */
    class MyStop extends TimerTask{
        public void run() {
            BucketThreadTester.th1.interrupt();
            BucketThreadTester.th2.interrupt();
        }
    }


}








