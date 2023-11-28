import java.sql.*;
import java.util.Scanner;

class databaseconn
{
    static Scanner s = new Scanner(System.in);
    static String Query = "";
    static Connection connect;
    static PreparedStatement ps;
    static Statement statement;

                                                                                //connection
    private boolean connectDatabase()
    {
        System.out.println("=============================================================================================================================================================");
        System.out.println("connecting to server!!!");
        try {
            String url="jdbc:mysql://localhost:3306/college";
            connect = DriverManager.getConnection(url,"root","srivatsan");
            System.out.println("Connection Successful");
            return true;
        }
        catch (SQLException e) {

            System.out.println("Connection Error\n" + e);
            return false;
        }
    }

    private void disconnectDatabase()
    {
        try {
            connect.close();
            System.out.println("Disconnect Successful");
        }
        catch(SQLException e)
        {
            System.out.println("Disconnect False \n"+e);
        }
    }


    public boolean getconnectDatabase()
    {
        return connectDatabase();
    }

    public void getdisconnectDatabase()
    {
        disconnectDatabase();
    }


}