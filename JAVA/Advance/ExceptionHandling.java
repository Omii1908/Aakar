/* 
 * types of error :
 *  i. compile-time error
 *      These are syntax based error, compiler tells the errors.
 * 
 *  ii. runtime error
 *      The code is absolutely fine, but during the execution it suddenly stop execution
 *      can be due to missing file or something else.
 * 
 *  iii. logical error
 *      logic based error, can be fixed by testing.
 * 
 *  Exception handling is all about handling runtime error, so that program keep executing with no error.
 * 
 * Exception Hierarchy
 *  Object
 *      -> throwable
 *          -> error
 *              -> ThreadDeath
 *              -> virtualMachineError
 *                  -> outOfMemory
 *              -> IOError
 *          -> exception
 *              -> RuntimeException (unchecked exception)
 *                  -> Arthemetic
 *                  -> ArrayIndexbound
 *                  -> NullPointer
 *              -> SQLException (checked exception)
 *              -> IOException (checked exception)
 */

package Advance;

public class ExceptionHandling {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;

        try {
            j = 18 / i;
        } catch (Exception e) {
            System.out.println("Something went wrong!! \nError : "+e);
        }

        try {
            j = 18/20;
            if (j==0){
                throw new ArithmeticException("Custom error message!!");
            }
        } catch (Exception e) {
            System.out.println("with throw\nsomething went wrong!! \nerror : "+e);
        }

        System.out.println(j);
        System.out.println("Bye!!");
    }
}
