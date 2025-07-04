package Advance;

/*
 * Note :
 * -> Always make sure that thread work with immutable data types,
 * or should avoid mutation as it will give rise to **race condition**.
 * 
 * -> Thread Safe - only one thread will work with the object at that time.
 */

 class Counter{
    int count;

    // use syncronized keyword so that it will call the method one at a time.
    public void increment(){
        count++;
    }
 }

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {

        Counter c = new Counter();

        Runnable obj1 = () -> {
            for(int i = 0; i<=2000;i++){
                c.increment();
            }
        };

        Runnable obj2 = () -> {
            for(int i = 0; i<=2000;i++){
                c.increment();
            }
        };

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        t1.start();
        t2.start();

        t1.join();      // join the thread with other and wait for their response.
        t2.join();

        System.out.println(c.count);
    }
}
