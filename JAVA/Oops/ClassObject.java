class ClassObject {
    int a = 10;
    int b =20;
    public static void main(String[] args) {
        ClassObject co = new ClassObject();
        System.out.println("Accessing Object & performing operation : "+(co.a+co.b));
        System.out.println("Accessing Object & performing operation : "+co.a+co.b);
        System.out.println("Note : Observe the difference between two operation...");
    }
}