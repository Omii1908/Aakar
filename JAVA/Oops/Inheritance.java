public class Inheritance extends Calculate {
    public int sub (int a, int b) {
        return a-b;
    }
    public int multi (int a, int b) {
        return a*b;
    }
    public int div (int a, int b) {
        return a/b;
    }

    public static void main(String[] args) {
        Inheritance Inherit = new Inheritance();
        System.out.println("Parent class(addition) : "+Inherit.add(10,5));
        System.out.println("Child class(subtraction) : "+Inherit.sub(10,5));
        System.out.println("Child class(multiply) : "+Inherit.multi(10, 5));
        System.out.println("Child class(division) : "+Inherit.div(10, 5));
    }
}
