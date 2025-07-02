package Advance;

interface SAMR {
    int add(int a, int b);
}

public class Lambda {
    public static void main(String[] args) {
        SAMR obj = (i,j) -> i+j;    //NOTE : i+j will be return with the return.

        int result = obj.add(8,7);
        System.out.println("Adding with lambda : "+result);
    }
}
