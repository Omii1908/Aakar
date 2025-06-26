public class Encapsulation {
    private int age = 11;
    String name = "Om";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Encapsulation encap = new Encapsulation();
        // accessing private variable..
        System.out.println(encap.getAge());

        // Updating the private variable and accesing it..
        encap.setAge(20);
        System.out.println(encap.getAge());

        // Accessing the public variable..
        System.out.println("Name : " + encap.name);
    }
}