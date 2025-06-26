public class MethodOverloading {
    int num;

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        MethodOverloading cal = new MethodOverloading();
        System.out.println(cal.add(5, 10, 20));
        System.out.println(cal.add(5, 10));
        System.out.println(cal.add(5.0, 10.0));
    }
}