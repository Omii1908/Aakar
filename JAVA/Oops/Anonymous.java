public class Anonymous {
    public Anonymous() {
        System.out.println("Object Created!!");
    }

    public void show() {
        System.out.println("In  Anonymous Show!!");
    }

    public static void main(String[] args) {
        new Anonymous(); // Anonymous Object!!
        new Anonymous(); // new object created!!
        new Anonymous().show();
    }
}