package Advance;

abstract class A {
    public abstract void show();
}

public class AbstractAnonymousInnerClass {
    public static void main(String[] args) {
        A obj = new A() {
            public void show(){
                System.out.println("In new show!!");
            }
        };
        obj.show();
    }
}
