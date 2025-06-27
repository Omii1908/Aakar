public class Polymorphism {
    public static void main(String[] args) {
        new A().show(); // Outputs: This is class A
        new B().show(); // Outputs: This is class B (runtime polymorphism)
        System.out.println("Dynamic Method Dispatch in Java");
    }
}

class A {
    public void show() {
        System.out.println("in A show!!");
    }
}

class B {
    public void show() {
        System.out.println("in B show!!");
    }
}