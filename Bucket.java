import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class Bucket manages an ArrayList of integer numbers
 */
public class Bucket{
    private ArrayList<Integer> bucket;
    private Lock bucketLock;
    /** Default constructor*/
    public Bucket(){
        bucket = new ArrayList<Integer>();
        bucketLock = new ReentrantLock();
    }
    /** add an element to the bucket
     * @param n: an integer number
     */
    public void add(int n){
        bucketLock.lock();
        try{
            bucket.add(n);
        }finally{
            bucketLock.unlock();
        }

    }
    /** Calculates and returns the average of the elements of the bucket  and removes all the elements from the bucket
     * @return data, an array of integers. The first element of the array is the median value of the bucket, and second one is the size of the bucket.
     * post condition: the elements of the bucket are removed
     */
    public int[] median(){
        bucketLock.lock();
        try{
            int[] data ={0,0};
            if(bucket.size()==0) {
                return data;
            }
            Collections.sort(bucket);
            int median = bucket.get(bucket.size()/2);
            data[0]=median;
            data[1]=bucket.size();
            reset();
            return data;
        }finally{
            bucketLock.unlock();
        }

    }
    /** Returns size of the bucket
     */
    public int getSize(){
        bucketLock.lock();
        try{
            return bucket.size();
        }finally{
            bucketLock.unlock();
        }

    }
    /** Removes all elements from the bucket
     */
    private void reset(){
        bucketLock.lock();
        try {
            bucket.clear();
        }finally{
            bucketLock.unlock();
        }
    }
}