import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin
{
    private static Scanner s =  new Scanner (System.in);
    private static databaseconn Database = new databaseconn();
    private static Student student = new Student();

    private static boolean loop = Database.getconnectDatabase();
    private static Election election = new Election();

    public static void main(String[] args)
    {
        while (loop)
        {
            System.out.println("=============================================================================================================================================================");
            System.out.println("\n\t\t\t\t\t\t\t\tWelcome ");
            System.out.println("1.Admin");
            System.out.println("2.Student Election");
            System.out.println("3.Register Student");
            System.out.println("4.Exit");
            System.out.print("\nEnter Option : ");
            try {
                switch (s.nextInt())
                {
                    case 1:
                        System.out.println("=============================================================================================================================================================");
                        System.out.print("Admin id : ");
                        if (s.next().equals("Srivatsan"))
                        {
                            System.out.print("Password : ");
                            if (s.next().equals("srivatsan"))
                                adminMenu();
                             else
                                System.out.println("Password Mismatch");
                        }
                        else
                        {
                            System.out.println("Invalid ID");
                        }
                        break;

                    case 2:
                        if(election.getLogin())
                            election.studentElectionMenu();
                        break;

                    case 3:
                        student.createStudent();
                        break;
                    case 4:
                        System.out.println("===============================================================================================================================================================");
                        loop = false;
                        Database.getdisconnectDatabase();
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid Input");
                break;
            }
        }
    }
    private static void adminMenu()
    {
        boolean loop  = true;
        while(loop)
        {
            try {
                System.out.println("=============================================================================================================================================================");
                System.out.println("\t\t\t\t\t\tAdmin_Menu");
                System.out.println("1.Student Settings");
                System.out.println("2.Election Settings");
                System.out.println("3.Reset This Program");
                System.out.println("4.<- Back");
                System.out.print("\nEnter Option : ");

                switch (s.nextInt())
                {
                    case 1:
                        studentSettings();
                        break;
                    case 2:
                        election.electionSettings();
                        break;
                    case 3:
                        System.out.print("Password : ");
                        if (s.next().equals("srivatsan"))
                        {
                            election.Reset();
                        }
                        else
                            System.out.println("Password Mismatch");

                        break;
                    case 4:
                        loop =false;
                        break;
                }


            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid Input");
            }


        }//while


    }//Admin_Menu()
    private static void studentSettings()
    {
        boolean loop  = true;
        while(loop)
        {
            try {
                String Query;
                System.out.println("=============================================================================================================================================================");
                System.out.println("\n\t\t\t\t\t\tStudent Settings");
                System.out.println("1.Show Student ");
                System.out.println("2.Show All Students");
                System.out.println("3.Delete Student");
                System.out.println("4.<- Back");
                System.out.print("\nEnter Option : ");
                switch (s.nextInt())
                {
                    case 1:
                        System.out.print("\nEnter Email : ");
                        Query = "Select * from students where email =\""+s.next()+"\"";
                        student.showStudent(Query);
                        break;
                    case 2:
                        Query = "Select * from Students ";
                        student.showAll(Query);
                        break;
                    case 3:
                        student.deleteStudent();
                        break;
                    case 4:
                        loop =false;
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid Input");
            }
        }
    }
}
