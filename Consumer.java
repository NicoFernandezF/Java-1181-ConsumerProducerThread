/**
 * Consumer is a runnable class that interacts with Bucket arraylist.
 */
public class Consumer implements Runnable{
    private Bucket bucket;

    public Consumer(Bucket b){
        this.bucket = b;
    }

    /**
     * Its run method prints the median and size of the bucket
     */
    @Override
    public void run() {
        while(Thread.interrupted() == false){
            int median = bucket.median()[0];
            int size = bucket.getSize();
            System.out.println("Median: "+ median+", Size: "+ size);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
