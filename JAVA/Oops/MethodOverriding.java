public class MethodOverriding extends Calculate {
    public int add(int a, int b, int c){
        return a+b+c;
    }
    public static void main(String[] args) {
        MethodOverriding mor = new MethodOverriding();
        Calculate cal = new Calculate(); 
        System.out.println("Normal Method call : "+cal.add(10,20));
        System.out.println("MethodOverriding : "+mor.add(10,20,30));
    }
}
