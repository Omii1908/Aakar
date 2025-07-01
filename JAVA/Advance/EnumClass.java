package Advance;

/*
 * Note :
 *  we can extend the enum class, but rest all can be done with it.
 */

enum Laptop {
    Macbook(2000), XPS(2200), Surface(1700), Thinkpad(1900);

    private int price;

    private Laptop(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

public class EnumClass {
    public static void main(String[] args) {
        Laptop lap = Laptop.Macbook;
        System.out.println(lap + " : " + lap.getPrice());

        // Accessing all the laptops
        System.out.println("\nAccessing all laptops");
        for (Laptop laps : Laptop.values()) {
            System.out.println(laps + " : " + laps.getPrice());
        }
    }
}
