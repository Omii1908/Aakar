package Advance;

class OmException extends RuntimeException {
    public OmException(String string) {
        super(string);
    }
}

public class CustomException {
    public static void main(String[] args) {
        int i = 20;
        int j = 0;

        try {
            j = 18 / i;
            if (j == 0) {
                throw new OmException("Custom Exception!!");
            }
        } catch (Exception e) {
            System.out.println("something went wrong!! error : " + e);
        }

        System.out.println(j);
        System.out.println("Bye!!");
    }
}
