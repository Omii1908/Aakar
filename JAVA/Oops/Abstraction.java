public class Abstraction {
    public static void main(String[] args) {
        // Abstract class cannot be instantiated
        // Car obj = new Car();
        Car obj = new WagonR();

        obj.drive();
        obj.playMusic();
    }
}

// abstract method only belong to abstract class
abstract class Car {
    public abstract void drive();

    public void playMusic() {
        System.out.println("playing Music!!");
    }
}

class WagonR extends Car {

    public void drive() {
        System.out.println("Driving..");
    }

}

/*
 * Note :
 * 1. Abtract method always belongs to abstract class
 * 2. Abstract class may or may not have abstract method
 * 3. Inherited class should have all the abstract method,
 * or it will be too counted as as a abstract class, and we have to create
 * another class.
 */