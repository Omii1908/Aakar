public class Constructor {
    int age;
    String name;

    //Default Constructor...
    public Constructor(){
        age = 12;
        name = "John";
    }

    //Parameterized Constructor...
    public Constructor(int age, String name){
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        Constructor con = new Constructor();
        System.out.println("Default Constructor :- \nage : "+con.age+"\nname : "+con.name);
        
        Constructor pcon = new Constructor(40, "Prakash");
        System.out.println("\nParameterized Constructor :- \nage : "+pcon.age+"\nname : "+pcon.name);
    }
}
