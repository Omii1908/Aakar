package Advance;

class A {
    int age;

    public void show() {
        System.out.println("in show!!");
    }

    class B {
        public void config() {
            System.out.println("in config of B!!");
        }
    }

    static class C {
        public void config() {
            System.out.println("in config of C!!");
        }
    }
}

public class InnerClass {
    public static void main(String[] args) {
        A obj = new A();
        obj.show();

        // calling the inner class
        A.B obj1 = obj.new B(); // for non-static class
        obj1.config();

        A.C obj2 = new A.C(); // for static class
        obj2.config();
    }
}

/*
 * Note(for inner class) :
 * -> We cannot make outer class static
 * -> for static inner class --> A.B obj1 = new A.B();
 * -> for non-static inner class --> A.B obj1 = obj.new B();
 */