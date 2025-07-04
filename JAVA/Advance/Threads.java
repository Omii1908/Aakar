/*
 * Thread :
 *  -> small-weighted process that work parallel.
 *  -> mostly handled by framework.
 *  -> to make a class thread, we simply need to extends it to thread.
 *  -> every thread need to start to execute the method.
 *  -> generally we won't be able to see that execution as it place in a split second if one get loaded earlier even for a second.
 * 
 * we can set the priority for each thread.
 *  -> max - Thread.MAX_PRIORITY
 *  -> normal - 
 *  -> low
 */
package Advance;

//creating multiple threads..
class Ab extends Thread {
    public void run() {
        for (int i = 0; i <= 25; i++) {
            System.out.println("Hello!!");
            try {
                Thread.sleep(10); // hold the thread for a while
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Ba extends Thread {
    public void run() {
        for (int i = 0; i <= 25; i++) {
            System.out.println("Hii!!");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Threads {
    public static void main(String[] args) {
        Ab obj1 = new Ab();
        Ba obj2 = new Ba();

        // Setting the priority for the thread
        obj2.setPriority(Thread.MAX_PRIORITY);

        obj1.start(); // start will call the run method.
        obj2.start();

        // while execution you will observer that sometime two of them are executed
        // simultaneously, this can be due to schedular select the same thread twice.
    }
}

/*
 * THREAD STATE :
 * i. NEW -> start() -> RUNNABLE
 * ii. RUNNABLE -> run() -> RUNNING
 * -> stop() -> DEAD
 * iii. RUNNING -> sleep()/wait() -> WAITING
 * -> stop() -> DEAD
 * iv. WAITING -> notify() -> RUNNABLE
 * v. DEAD
 */