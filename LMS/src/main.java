package src;
//IMPORTED JAVA LIBRARIES---
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.sql.*;

public class main {
    //Establishing the connection---
    Connection con = null;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Scanner scan = new Scanner(System.in);
    void connect(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try{
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","<username>", "<password>");
                con.setAutoCommit(true);
            }catch(SQLException e1){
                System.out.println("\n\tUnable to connect with the Database...\n\tPlease Restart the Application!!\n");
            }
        }catch(Exception e1){
            System.out.println("\n\tDriver is missing...\n\tPlease fix the driver!!\n");
        }
    }


    //Option : Searching book
    void search(){
        try {
            System.out.print("\n\t**Searching Tab**\nEnter the name of book : ");
            String search = in.readLine();

            //Searching the book from database--
            String q = "Select * from book_details where  BOOK_NAME = ? and status = ? order by accession";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1,search);
            pst.setString(2,"Y");
            ResultSet set = pst.executeQuery();

            //Displaying the Available book--
            int n=1;
            while (set.next()) {
                if(n==1){
                    n++;
                    System.out.println("\n\n\t**Book Available.. \n\tAcc no. \tBook Name  \tAuthor Name \tPublication Name \tGenre \tEdition \tYear of Publish");
                }
                System.out.println("\t\t"+set.getInt(1)+"\t"+set.getString(2)+"\t"+set.getString(3)+"\t"+set.getString(4)+"\t"+set.getString(5)+"\t"+set.getString(6)+"\t"+set.getString(7));
            }
            set.close();
            pst.close();
            if (n==1){
                System.out.println("\t\tNo Books Available\n\n");
                return;
            }
            System.out.print("\n\nDo you Want to issue the book?\n1. for 'Yes\n2. for 'No\n[CHOICE] : ");
            int choice = Integer.parseInt(in.readLine());
            if(choice==1){
                issueBook();
            }
            else {
                System.out.println("\n\n");
            }
            return;
        }catch (Exception e){
            System.out.println(e+"\n\tServer Down!!\n\tTry again!!\n\n");
            return;
        }
    }


    //Option : Book related queries--
    void books(){
        //Creating objects
        main lib = new main();

        while (true){
            System.out.print("\n\n\t**BOOKS RELATED QUERIES**\n1. Back\n2. New Book Entry\n3. Delete Book\n4. Exit\n\n[CHOICE] : ");
            int ch = scan.nextInt();
            switch (ch){
                //Returning back--
                case 1 :
                    System.out.println("\n\n");
                    return;


                //Inserting record in table--
                case 2 :
                    try {
                        String q = "select accession from book_details order by accession desc";
                        PreparedStatement pst = con.prepareStatement(q);
                        ResultSet set = pst.executeQuery();
                        set.next();
                        int acc = set.getInt(1)+1;

                        //Taking data from user--
                        System.out.print("\n**Fill the details : \nAccession no. : "+acc);
                        System.out.print("\nEnter the Book Name : ");
                        String bn = in.readLine();
                        System.out.print("\nEnter the Author Name : ");
                        String an = in.readLine();
                        System.out.print("\nEnter the Name of Publisher: ");
                        String pc = in.readLine();
                        System.out.print("\nEnter the genre of the book : ");
                        String gen = in.readLine();
                        System.out.print("\nEnter the Edition (no. only): ");
                        int ed = Integer.parseInt(in.readLine());
                        System.out.print("\nEnter the Year of Publication(YYYY): ");
                        int yop = Integer.parseInt(in.readLine());

                        //no of books--
                        System.out.print("Enter the no of books : ");
                        int n = Integer.parseInt(in.readLine());

                        //Inerting the data in table for n no. of books--
                        q = "insert into book_details values(?,?,?,?,?,?,?,?)";
                        pst = con.prepareStatement(q);
                        while (n!=0){
                            pst.setInt(1,acc);
                            pst.setString(2,bn);
                            pst.setString(3,an);
                            pst.setString(4,pc);
                            pst.setString(5,gen);
                            pst.setInt(6,ed);
                            pst.setInt(7,yop);
                            pst.setString(8,"Y");
                            pst.executeUpdate();
                            acc=acc+1;
                            n--;
                        }
                        System.out.println("\n\tSUCCESSFULLY INSERTED!!\n");
                        pst.close();
                    }catch (Exception e){
                        System.out.println("\n\t404 SOMETHING WENT WRONG!!\n");
                    }
                    break;


                case 3 :
                    try {
                        String q = "delete from book_details where Accesssion = ?";
                        PreparedStatement pst = con.prepareStatement(q);

                        //Taking data from user--
                        System.out.print("\n**Deleting the book..\nEnter the Accession no. (no. only) : ");
                        int acc = Integer.parseInt(in.readLine());
                        System.out.print("\n\nDo you really Want to delete the book?\n1. for 'Yes\n2. for 'No\n[CHOICE] : ");
                        int choice = Integer.parseInt(in.readLine());
                        if(choice==1){
                            pst.setInt(1,acc);
                            System.out.println("\n\tSUCCESSFULLY DELETED!!\n");
                            pst.close();
                        }
                        else {
                            System.out.println("\n\n");
                        }
                        return;
                    }catch (Exception e){
                        System.out.println("\n\t404 SOMETHING WENT WRONG!!\n");
                    }
                    break;


                //Closing all operations--
                case 4 :
                    lib.closeP();

                //For wrong Input--
                default:
                    System.out.println("\n\tWrong input!!\n\tPlease re-enter the value..\n");
            }
        }
    }

    //Option : Members related queries--
    void members(){
        //Creating objects
        String q;
        main lib = new main();
        while (true){
            System.out.print("\n\n\t**MEMBERS RELATE QUERIES**\n1. Back\n2. New Member Entry\n3. Update Member Details\n4. Deactivate your Account\n5. Exit\n\n[CHOICE] : ");
            int ch = scan.nextInt();
            scan.reset();
            switch (ch){
                case 1 :
                    System.out.println("\n\n");
                    return;
                //Inserting the data--
                case 2 :
                    try {
                        q = "select member_id from member_details order by member_id desc";
                        PreparedStatement pst = con.prepareStatement(q);
                        ResultSet set = pst.executeQuery();
                        set.next();
                        int id = set.getInt(1)+1;

                        //Taking data from user--
                        System.out.print("\n\t**Fill the details : \nMember ID : "+id);
                        System.out.print("\nEnter Your Member Name : ");
                        String mn = in.readLine();
                        System.out.print("\nEnter Your Phone No. : ");
                        int pn = Integer.parseInt(in.readLine());
                        System.out.print("\nEnter Your Address : ");
                        String add = in.readLine();
                        System.out.print("\nEnter Your Department : ");
                        String dept = in.readLine();

                        //Inserting value in table--
                        q = "insert into Member_details values(?,?,?,?,?,?)";
                        pst = con.prepareStatement(q);
                        pst.setInt(1,id);
                        pst.setString(2,mn);
                        pst.setInt(3,pn);
                        pst.setString(4,add);
                        pst.setString(5,dept);
                        pst.setString(6,"ACTIVE");
                        pst.executeUpdate();
                        System.out.println("\n\tSUCCESSFULLY INSERTED!!\n");
                        pst.close();
                    }catch (Exception e){
                        System.out.println(e+"\n\t404 SOMETHING WENT WRONG!!\n");
                    }
                    break;
                //Updating the data--
                case 3 :
                    try {
                        q = "update Member_details set PHONE_NO = ?,  ADDRESS = ?, DEPARTMENT = ? where MEMBER_ID = ?";
                        PreparedStatement pst = con.prepareStatement(q);

                        //Taking data from user--
                        System.out.print("\n\t**Update the details : \nEnter Your Member No.(No. Only) : ");
                        int id = Integer.parseInt(in.readLine());
                        System.out.print("\nEnter Your New Phone No. : ");
                        int pn = Integer.parseInt(in.readLine());
                        System.out.print("\nEnter Your New Address : ");
                        String add = in.readLine();
                        System.out.print("\nEnter Your New Department : ");
                        String dept = in.readLine();

                        //Updating value in table--
                        pst.setInt(1,pn);
                        pst.setString(2,add);
                        pst.setString(3,dept);
                        pst.setInt(4,id);
                        pst.executeUpdate();
                        System.out.println("\n\tSUCCESSFULLY UPDATED!!\n");
                        pst.close();
                    }catch (Exception e){
                        System.out.println("\n\t404 SOMETHING WENT WRONG!!\n");
                    }
                    break;
                //Deactivating the Account--
                case 4 :
                    try {
                        //Taking data from user--
                        System.out.print("\n\t**Deactivating the Account**\nEnter Your Member No.(No. Only) : ");
                        int id = Integer.parseInt(in.readLine());
                        System.out.print("\n\tPlease Confirm your Deactivation!!\n\t1. for 'yes'\n\t2. for 'no'\n[CHOICE] : ");
                        int choice = Integer.parseInt(in.readLine());
                        if (choice==1){
                            q = "update Member_details set STATUS = ? where MEMBER_ID = ?";
                            PreparedStatement pst = con.prepareStatement(q);
                            pst.setString(1,"EXPIRED");
                            pst.setInt(2,id);
                            pst.executeUpdate();
                            System.out.println("\n\tSUCCESSFULLY DEACTIVATED!!\n");
                            pst.close();
                        }else {
                            System.out.println("\n\tOPERATION CANCELED!!\n");
                        }
                    }catch (Exception e){
                        System.out.println("\n\t404 SOMETHING WENT WRONG!!\n");
                    }
                    break;
                //Closing the Operation--
                case 5 :
                    lib.closeP();
                default:
                    System.out.println("\n\tWrong input!!\n\tPlease re-enter the value..\n");
            }
        }
    }


    //Option : Issue Book
    void issueBook(){
        try {
            //Taking data from user--
            System.out.print("\n\t**Issuing the Book.. \nEnter Your Member No.(No. Only) : ");
            int id = Integer.parseInt(in.readLine());
            System.out.print("\nEnter Your Accession No. : ");
            int acc = Integer.parseInt(in.readLine());

            //Updating the book table--
            String q = "Update book_details set STATUS = 'N' where ACCESSION = ? and STATUS = 'Y'";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1,acc);
            int flag  = pst.executeUpdate();
            //Checking if book is already issued or not--
            if(flag > 0 ){
                //Inserting the values in IssueBook--
                q = "insert into issue_book(member_id,Accession,fine) values(?,?,0)";
                pst = con.prepareStatement(q);
                pst.setInt(1,id);
                pst.setInt(2,acc);
                pst.executeUpdate();
                pst.close();
                System.out.println("\n\tSUCCESSFULLY ISSUED!!\n");
            }
            else{
                System.out.println("\n\tALREADY ISSUED!!\n\n");
            }
        }catch (Exception e){
            System.out.println("\n\t404 SOMETHING WENT WRONG!!\n\n");
        }
        return;
    }


    //Option : Returning the book--
    void returnBook(){
        try {
            //Checking the no. of book issued--
            System.out.print("\n\t**Returning the Book.. \nEnter Your Member No.(No. Only) : ");
            int id = Integer.parseInt(in.readLine());
            String q = "select book_details.accession,book_details.book_name,book_details.author_name,issue_book.issue_date from book_details inner join issue_book on book_details.accession = issue_book.accession where book_details.status = 'N' and issue_book.member_id = ?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1,id);
            ResultSet set = pst.executeQuery();

            //Displaying the books that are issued--
            int n=1,i=0;
            int ac[]={0,0,0,0,0};
            String date[]={"","","","",""};
            while (set.next()) {
                if(n==1){
                    n++;
                    System.out.println("\n\n\t**Book issued.. \nAcc no. \tBook Name  \t\t\tAuthor Name \tIssued_date");
                }
                ac[i]=set.getInt(1);
                date[i]=set.getString(4);
                System.out.println("\t"+set.getInt(1)+"\t"+set.getString(2)+"\t"+set.getString(3)+"\t"+set.getString(4));
                i++;
            }

            //If no book is issued--
            if (n==1){
                System.out.println("\t\tNo Books Issued\n\n");
                return;
            }else {
                //Taking input form user--
                System.out.print("\nEnter Your Accession No. : ");
                int acc = Integer.parseInt(in.readLine());
                System.out.print("\nDate Format : [DD-MM-YYYY]\nEnter the Today's Date : ");
                String ret = in.readLine();

                //Calculating the fine--
                for (i = 0; i < ac.length; i++) {
                    if (ac[i]==acc){
                        break;
                    }
                }
                LocalDate sd = LocalDate.parse(date[i].substring(0,10));
                LocalDate ed = LocalDate.parse(ret.substring(6,10)+ret.substring(2,6)+ret.substring(0,2));
                Period pd = Period.between(sd,ed);
                int days = pd.getDays();
                int fin = 0;
                String op = "PAID";
                if(days>7){
                    fin = days*10;
                    System.out.print("\n\n\t**Fine--\nTotal Fine : "+fin+" [ for "+days+" Days ]\n\nDid the Student paid the Amount ?\n1. for 'PAID'\n2. for 'UNPAID'\n[OPTION] : ");
                    int ch = Integer.parseInt(in.readLine());
                    if (ch == 2) {
                        op = "UNPAID";
                    }
                }

                //Updating the book_details table--
                q = "Update book_details set STATUS = 'Y' where ACCESSION = ? and STATUS = 'N'";
                pst = con.prepareStatement(q);
                pst.setInt(1,acc);
                int flag = pst.executeUpdate();

                //Updating the IssueBook table--
                if(flag > 0 ){
                    q = "Update issue_book set RETURN_DATE = ?,FINE = ?,AMOUNT_DUE = ? where ACCESSION = ?";
                    pst = con.prepareStatement(q);
                    pst.setString(1,ret);
                    pst.setInt(2,fin);
                    pst.setString(3,op);
                    pst.setInt(4,acc);
                    pst.executeUpdate();
                    pst.close();
                    System.out.println("\n\tSUCCESSFULLY RETURN!!\n\n");
                }
                else{
                        System.out.println("\n\tALREADY RETURNED!!\n\n");
                }
            }
        }catch (Exception e){
            System.out.println("\n\t404 SOMETHING WENT WRONG!!\n\n");
        }
        return;
    }


    //Option : closing Operations--
    void closeP(){
        //Confirmation of exit--
        System.out.print("\n\n\t**EXIT MENU**\nDo you really want to exit ?\n1. for 'yes'\n2. for 'no'\n[CHOICE] : ");
        int choice = scan.nextInt();
        if (choice == 1){
            System.out.println("\nCLOSING CONNECTION...\n\nTHANK YOU & HAVE A NICE DAY!!");
            System.exit(0);
        }else{
            System.out.println("\n\n");
            return;
        }
    }

    //main---
    public static void main(String[] args) {
        System.out.println("\n\n\t\tE-LIBRARY PORTAL\n");
        //Checking the connection--
        main lib = new main();
        lib.connect();
        if(lib.con==null)
            lib.closeP();
        else
            System.out.println("\n\tConnection Established with Server!!\n\nWELCOME TO THE E-LIBRARY MANAGEMENT SYSTEM!!\n");

        //To maintain the cycle--
        while (true) {
            //Menu--
            System.out.print("\t*HOME PAGE*\n1. Search a Book\n2. Recommend a Book\n3. Books related Queries \n4. Members related Queries\n5. Issue Book\n6. Return Book\n7. Exit\n[CHOICE] : ");
            int ch = lib.scan.nextInt();
            lib.scan.reset();
            //Processing the option--
            switch (ch) {
                case 1 :
                    lib.search();
                    break;
                case 3 :
                    lib.books();
                    break;
                case 4 :
                    lib.members();
                    break;
                case 5 :
                    lib.issueBook();
                    break;
                case 6 :
                    lib.returnBook();
                    break;
                case 7:
                    lib.closeP();
                default:
                    System.out.println("\n\tWrong input!!\n\tPlease re-enter the value..\n");
            }
        }
    }
}
