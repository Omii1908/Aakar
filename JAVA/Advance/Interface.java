/*
 * -> interface is not a class, & by default each & every method is a public abstract.
 * -> mostly used in project, as it contain design of the project, so we have to initiate it.
 * -> interface doesn't have memory since they are implemented using a class,
 *    so objects defined inside the interface is static and final.
 * -> class can implements multiple interface, unlike abstract class that can be extended only one.
 *      class B implements X,Y: (all method should be used)     interface
 *      class B extends A: (all function need to defined)       abstract class
 */

package Advance;

interface AB {
    int age = 14;
    String area = "Mumbai";

    void show();

    void config();
}

class BA implements AB {
    // like abstract class it too needs to implement all the methods of interface.
    public void config() {
        System.out.println("in config!!");
    }

    public void show() {
        System.out.println("in show!!");
    }
}

public class Interface {
    public static void main(String[] args) {
        // initiatating the interface
        AB obj = new BA();
        obj.config();
        obj.show();
    }
}
