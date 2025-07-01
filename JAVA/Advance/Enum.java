package Advance;
/*
 * Note :
 *  -> Enum is a class but can't be extended like other class.
 *  -> it's contents will be treated as object.
 */

//Declaring a enum class
enum Status {
    Running, Failed, Pending, Success;
}

public class Enum {
    public static void main(String[] args) {
        Status s = Status.Running;
        System.out.println(s);

        // index of the enum's objects
        System.out.println(s.ordinal()); // each object in status has a index similar to array

        // access all
        Status[] st = Status.values(); // values return a array
        for (Status ss : st) {
            System.out.println(ss);
        }

        // comparing the enum
        switch (s) {
            case Running:
                System.out.println("All Good!!");
                break;
            case Failed:
                System.out.println("Try Again!!");
            case Pending:
                System.out.println("Please Wait!!");

            default:
                System.out.println("Done!!");
                break;
        }
    }
}
