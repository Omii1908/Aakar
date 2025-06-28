class A {
    public void show() {
        System.out.println("in A show");
    }
}

class B extends A {
    public void show() {
        System.out.println("In extended new show!!");
    }
}

public class AnonymousInnerClass {
    public static void main(String[] args) {
        // How can we change the functionality of any method?

        // 1. Method Overriding
        System.out.println("Through Method Overriding : ");
        B obj = new B();
        obj.show();

        // 2. Anonymous Inner Class
        System.out.println("Through Anonymous Inner class : ");
        A objA = new A() {
            public void show() {
                System.out.println("In inner new show!!");
            }
        };
        objA.show();
    }
}