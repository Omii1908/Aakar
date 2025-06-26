public class ThisSuper {
    public ThisSuper(){
        super();
        System.out.println("in ThisSuper!!");
    }

    public ThisSuper(int n){
        super();
        System.out.println(n+" in ThisSuper!!");
    }

    public static void main(String[] args) {
        new ThisSuper();
        new ThisSuper(5);
    }
}