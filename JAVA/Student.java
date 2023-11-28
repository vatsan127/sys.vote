import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends databaseconn
{
    public void createStudent()
    {
        System.out.println("=============================================================================================================================================================");

        try {
            System.out.println("Register Student");
            Query = "Insert into Students Values (?,?,?,?,?,?)";
            ps = connect.prepareStatement(Query);
            System.out.print("Enter Name : ");
            ps.setString(1,s.next().toUpperCase());
            System.out.print("Enter DOB : ");
            ps.setString(2,s.next());
            System.out.print("Enter DEPT : ");
            ps.setString(3,s.next().toUpperCase());
            System.out.print("Enter Batch : ");
            ps.setInt(4,s.nextInt());
            System.out.print("Enter Email : ");
            ps.setString(5,s.next().toLowerCase());
            System.out.print("Enter Password : ");
            String pass1 = s.next();
            System.out.print("Confirm Password : ");
            String pass2 = s.next();

            if(pass1.equals(pass2))
                ps.setString(6,pass1);
            else {
                System.out.println("Password mismatch");
                return ;
            }
            ps.executeUpdate();
            ps.close();
            System.out.println("Registration Successful");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Error\n"+e);
        }
    }
    public void deleteStudent()
    {
        try {
            System.out.println("=============================================================================================================================================================");
            Query = "Delete from Students where email = ?";
            ps = connect.prepareStatement(Query);
            System.out.print("Enter Registered Email (Full-Email-ID) : ");
            ps.setString(1,s.next());
            ps.executeUpdate();
            ps.close();
            System.out.println("Delete Success");
        }
        catch(SQLException e)
        {
            System.out.println("Delete Failed\n"+e);
        }
    }
    public void showStudent(String Query)
    {
        System.out.println("=============================================================================================================================================================");

        try
        {
            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(Query);
            if(rs.next())
            {
                System.out.println("Name  : "+ rs.getString("Name"));
                System.out.println("DOB   : "+ rs.getString("DOB"));
                System.out.println("DEPT  : "+ rs.getString("Dept"));
                System.out.println("Batch : "+ rs.getString("Batch"));
                System.out.println("Email : "+ rs.getString("Email"));
            }
            else
                System.out.println("Student Not Found");
            statement.close();
            rs.close();
        }
        catch(SQLException e)
        {
            System.out.println("Data Fetching Error" + e);
        }
    }
    public void showIndex()
    {
        System.out.println("=============================================================================================================================================================");
        System.out.print("NAME       ");
        System.out.print("         DOB   ");
        System.out.print("   DEPT  ");
        System.out.print("  Batch");
        System.out.println("    Email");
    }
    public void showAll(String Query)
    {
        try {
            showIndex();
            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(Query);

                while (rs.next())
                {
                    System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                    String name = rs.getString("Name");
                    System.out.print(name);
                    for (int i = name.length(); i < 16; i++)
                        System.out.print(" ");
                    System.out.print(" ");

                    String DOB = rs.getString("DOB");
                    System.out.print(DOB);
                    System.out.print("  ");

                    String DEPT = rs.getString("Dept");
                    System.out.print(DEPT);

                    for (int i = DEPT.length(); i < 5; i++)
                        System.out.print(" ");
                    System.out.print("    ");

                    System.out.print(rs.getInt("Batch"));
                    System.out.print("  ");

                    System.out.println(rs.getString("Email"));
                }
                statement.close();
                rs.close();
        }
        catch(SQLException e)
        {
            System.out.println("Data Fetching Error\n" + e);
        }


    }
}
