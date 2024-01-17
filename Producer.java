/**
 * Producer runnable class interacts with Bucket arraylist
 */

public class Producer implements Runnable {
    private Bucket bucket;

    public Producer(Bucket b){
        this.bucket = b;
    }

    /**
     * Its run method adds integer numbers to the bucket via the generateNum() method
     */
    @Override
    public void run() {
        while(Thread.interrupted() == false) {
            bucket.add(generateNum());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * generateNum() generates random numbers up to 255
     * @return integer random number
     */
    public int generateNum(){
        int random = (int)(Math.random()*255);
        return random;
    }
}

