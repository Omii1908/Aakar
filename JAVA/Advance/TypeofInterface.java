/*
 * TYPES OF INTERFACE :
 *  i. Normal Interface
 *      interface with 2 or more method.
 * 
 *  ii. Functional/SAM(Single Abstract Method)
 *      interface that has only one method.
 *      lambda expression is used to initiate the interface(JAVA 8 update).
 * 
 *  iii. Marker
 *      interface that has no method.
 *      need because it is used to update something to the compiler.
 *      to implement serialization.
 */


package Advance;

//Functional Interface
@FunctionalInterface
interface SAM {
    void show();
}

public class TypeofInterface {
    public static void main(String[] args) {
        //JAVA 7 way of calling functional interface
        SAM obj = new SAM() {
            public void show(){
                System.out.println("in show!!");
            }
        };
        obj.show();

        //JAVA 8 : Lambda expression
        SAM objl = () -> {
            System.out.println("in show(lambda expression)!!");
        };
        objl.show();
    }
}
