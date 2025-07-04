package Advance;

//Creating thread from Runnable
class Abb implements Runnable {
    public void run() {
        for (int i = 0; i <= 25; i++) {
            System.out.println("Hello!!");
            try {
                Thread.sleep( 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Baa implements Runnable {
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

// Note : Runnable doesn't have start(), and so we need to create separate Thread for that purpose.

public class RunnnableThreads {
    public static void main(String[] args) {
        Runnable obj1 = new Abb();
        Runnable obj2 = new Baa();

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        t1.start();
        t2.start();

    }
}