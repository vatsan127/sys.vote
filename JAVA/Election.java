import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Election extends databaseconn
{
    private String Email;
    private String Name;
    private boolean Election,Registration,Vote,Result;

    private Student student = new Student();

    Election()
    {
        try
        {
            Query = "select * from election\n" +
                    "limit 1;";
            statement = connect.createStatement();
            ResultSet rs =statement.executeQuery(Query);
            rs.next();
            Election = Boolean.parseBoolean(rs.getString("Election"));
            Registration = Boolean.parseBoolean(rs.getString("Registration"));
            Vote = Boolean.parseBoolean(rs.getString("Vote"));
            Result = Boolean.parseBoolean(rs.getString("Result"));
            rs.close();
            statement.close();
        }
        catch (SQLException e )
        {
            System.out.println("Election Permission Error\n"+e);
        }
    }

    private boolean login()
    {

        try
        {
            System.out.println("=============================================================================================================================================================");

            System.out.print("Enter Email : ");
            Email = s.next();
            Query = "Select * from students where Email = '"+Email+"';";

            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(Query);

            if(rs.next())
            {
                Name = rs.getString("Name");
                Email = rs.getString("Email");
                System.out.print("Enter Password : ");
                if(s.next().equals(rs.getString("Password")))
                {
                    System.out.println("Login Successful");
                    return true;
                }
                else
                    System.out.println("Invalid Password");
            }
            else
            {
                System.out.println("Student Not Found");
                System.out.println("Register Student");
            }
            statement.close();
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("Student Not Found\n"+e);
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid Input");
        }
        catch(Exception e)
        {
            System.out.println("Error Login\n"+e);
        }

        return false;
    }

    public void electionSettings()
    {
        boolean loop  =true;

        while(loop)
        {
            try
            {
                System.out.println("=============================================================================================================================================================");
                System.out.println("\t\t\t\t\t\tElection Settings");
                System.out.println("1.Show Canditates");
                System.out.println("2.Show Students Voted");
                System.out.println("3.Manage permissions");
                System.out.println("4.<- Back");
                System.out.print("\nEnter Option : ");

                switch(s.nextInt())
                {
                    case 1:
                        showCanditates();
                        break;
                    case 2:
                        String Query = """
                                select s.name,s.DOB,s.DEPT,s.batch,s.email
                                from students s
                                join Voters v
                                on\s
                                s.email = v.email;""";
                        student.showAll(Query);
                        break;
                    case 3:
                        managePermissions();
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

    private void managePermissions()
    {
        System.out.println("=============================================================================================================================================================");
        System.out.println("Enable/Disable Settings");
        try {
            if (Registration)
            {
                System.out.println("1.Enable Voting");
                System.out.print("Enter Option : ");
                if(s.nextInt()==1) {
                    Query = """
                            Update Election
                            Set
                            vote ="true",
                            Registration ="false",
                            Result = "false"
                            where election ="true";""";
                    Registration = !Registration;
                    Vote = !Vote;
                    System.out.println("Vote Enabled");
                }
                else
                    System.out.println("Invalid Input");

            }
            else if(Vote)
            {
                System.out.println("1.Enable Result");
                System.out.print("Enter Option : ");
                if(s.nextInt()==1) {
                    Query = """
                            Update Election
                            Set\s
                            vote ="false",
                            Registration ="false",
                            Result = "true"
                            where election ="true";""";
                    Vote =!Vote;
                    Result = !Result;
                    System.out.println("Result Enabled");
                }
                else
                    System.out.println("Invalid Input");
            } else if (Result)
            {
                System.out.println("Election Over !!! Results Announced ");
                return;
            }
            statement = connect.createStatement();
            statement.executeUpdate(Query);
            statement.close();

        }
        catch(SQLException e)
        {
            System.out.println("Permission Update Error\n"+e);
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid Input");
        }
    }

    public void studentElectionMenu()
    {
            boolean loop = true;
            while(loop)
            {
                System.out.println("=============================================================================================================================================================");
                System.out.println("\n\t\t\t\t\t\tStudent Election");
                System.out.println("1.Register Canditate ");
                System.out.println("2.Show Canditates ");
                System.out.println("3.✔ Vote");
                System.out.println("4.Result");
                System.out.println("5.‹- Back \n");
                System.out.print("Enter Option : ");
                try
                {
                    switch (s.nextInt())
                    {
                        case 1:
                            registerCanditate();
                            break;

                        case 2:
                            showCanditates();
                            break;

                        case 3:
                            vote();
                            break;

                        case 4:
                            Result();
                            break;
                        case 5:
                            loop = false;
                            break;
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid Input");
                }
            }
        }

    public void showCanditates()
    {
        System.out.println("=============================================================================================================================================================");
        System.out.println("\n\n\n----------------------------President---------------------------------");
        Query = """
                                    Select s.Name,s.DOB,s.batch,s.dept,s.email
                                     from Students s
                                     Join Canditates c
                                    on s.email = c.email
                                     where c.post = "President";
                                    """;
        student.showAll(Query);
        System.out.println("=============================================================================================================================================================");

        System.out.println("\n\n\n----------------------------Vice President----------------------------");
        Query = """
                                    Select s.Name,s.DOB,s.batch,s.dept,s.email
                                     from Students s
                                     Join Canditates c
                                    on s.email = c.email
                                     where c.post = "Vice President";""";
        student.showAll(Query);
        System.out.println("=============================================================================================================================================================");

        System.out.println("\n\n\n----------------------------Sports Captain----------------------------");
        Query = """
                                    Select s.Name,s.DOB,s.batch,s.dept,s.email
                                     from Students s
                                     Join Canditates c
                                    on s.email = c.email
                                     where c.post = "Sports Captain";""";
        student.showAll(Query);
        System.out.println("=============================================================================================================================================================");

    }

    private boolean checkEligibility(String Query)
    {
        try {
            //Query = "Select * from canditates where Email = \"" + Email + "\"";
            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(Query);
            if (rs.next())
            {
                return true;
            }
            rs.close();
            statement.close();
        }
        catch(SQLException e)
        {
            System.out.println("Server Side Error\n"+e);
        }
        return false;
    }

    private boolean checkAvailable(String Role, int Limit)
    {
        //boolean val = true;
        System.out.println("Checking Availability Plz Wait");
        try
        {
            statement = connect.createStatement();
            Query = "Select Count(*) From Canditates\n" +
                    "where Post = \"" + Role + "\";";
            ResultSet rs = statement.executeQuery(Query);
            rs.next();
            if (rs.getInt("Count(*)") >= Limit)
                return false;
            //val = false;
            statement.close();
            rs.close();
        }
        catch (SQLException e) {
            System.out.println("check_available ERROR\n" + e);

        }
        return true;
    }

    private void vote()
    {
        if(!Vote) {
            System.out.println("=============================================================================================================================================================");
            System.out.println("Voting is Not open");
            return;
        }

        Query="Select * from Voters where Email = \""+Email+"\"";
        if(checkEligibility(Query))
        {
            System.out.println(Name + " You have already casted your Vote");
        }
        else
        {
            System.out.println("\n\n\n----------------------------President---------------------------------");
            Query = """
                        Select c.ID ,s.name ,c.post
                        from canditates c
                        Join Students s On
                        s.email = c.email
                        where Post = "President";
                        """;
            castVote(Query);
            System.out.println("\n\n\n----------------------------Vice President----------------------------");
            Query = """
                        Select c.ID ,s.name ,c.post
                        from canditates c
                        Join Students s On
                        s.email = c.email
                        where Post = "Vice President";
                        """;
            castVote(Query);
            System.out.println("\n\n\n----------------------------Sports Captain----------------------------");
            Query = """
                        Select c.ID ,s.name ,c.post
                        from canditates c
                        Join Students s On
                        s.email = c.email
                        where Post = "Sports Captain";
                        """;
            castVote(Query);
            try {
                statement = connect.createStatement();
                Query ="Insert into Voters(Email) values(\""+Email+"\");";
                statement.executeUpdate(Query);
                statement.close();
            }
            catch(SQLException e)
            {
                System.out.println("Error IN Updating Voters Table ");
            }
        }
    }

    private void registerCanditate()
    {
        System.out.println("=============================================================================================================================================================");
        if(!Registration)
        {
            System.out.println("Registration is not Open");
            return;
        }
        try
        {

            System.out.println("\n\t\t\t\t\t\tCanditate Registration\n");
            System.out.print("Hello ");

            Query ="Select * from canditates where Email = \"" + Email + "\"";
            if(checkEligibility(Query))
            {
                System.out.println(Name+" You Are Already Contesting in Election");
                return;
            }

            System.out.println(Name+ " Choose  Role ");
            System.out.println("1.President");
            System.out.println("2.Vice President");
            System.out.println("3.Sports Captain");
            System.out.println("4.Exit");
            System.out.print("\nEnter Option : ");

            int option =s.nextInt();
            String Role = "";

            if(option ==1)
            {
                Role = "President";
                if(!(checkAvailable(Role,2)))
                {
                    System.out.println("Sorry " + Name + " the "+ Role+" is Full !!!");
                    return;
                }
            }
            else if (option == 2 )
            {
                Role = "Vice President";
                if(!(checkAvailable(Role,3)))
                {
                    System.out.println("Sorry " + Name + " the "+ Role+" is Full !!!");
                    return;
                }
            }
            else if(option == 3)
            {
                Role = "Sports Captain";
                if(!(checkAvailable(Role,4)))
                {
                    System.out.println("Sorry " + Name + " the "+ Role+" is Full !!!");
                    return;
                }
            }
            else if (option ==4 )
                return;
            else
                System.out.println("Enter Correct Option");

            Query = "Insert into Canditates(Email,Post) Values (?,?)";
            ps = connect.prepareStatement(Query);
            ps.setString(1,Email);
            ps.setString(2,Role);
            ps.executeUpdate();
            ps.close();
            System.out.println("Hey! "+Name+" Registration Successful");
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid Input");
        }
        catch (Exception e)
        {
            System.out.println("Registration Failed\n"+e);
        }
    }
    private void castVote(String Query)
    {
        try
        {
            statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(Query);
            System.out.println("=============================================================================================================================================================");
            System.out.print("ID   ");
            System.out.print("NAME");
            System.out.println("                Post       ");
            System.out.println("_______________________________________________________________________________________________________________________________________________________________");

            while (rs.next())
            {
                System.out.print(rs.getInt("ID"));
                System.out.print("   ");
                String name = rs.getString("Name");
                System.out.print(name);

                for (int i = name.length(); i < 16; i++)
                    System.out.print(" ");
                System.out.print(" ");

                System.out.println(rs.getString("Post"));
                System.out.println("_______________________________________________________________________________________________________________________________________________________________");
            }
            System.out.println("Note : Enter the ID no to cast vote to the Respective person");
            System.out.print("Enter ID : ");

            Query = "Update Canditates\n" +
                    "Set vote =vote +1\n" +
                    "where ID = "+s.nextInt()+";";
            statement.executeUpdate(Query);
            statement.close();
            rs.close();

        }
        catch (SQLException e)
        {
            System.out.println("Voting Error\n"+e);
        }

        catch(InputMismatchException e)
        {
            System.out.println("Invalid Input");
        }

    }


    public void Reset()
    {
        try {
            System.out.println("=============================================================================================================================================================");
            statement = connect.createStatement();
            Query = "Truncate table Voters;";
            statement.execute(Query);
            Query = "Truncate table Election;";
            statement.execute(Query);
            Query = "Truncate table canditates;";
            statement.execute(Query);

            System.out.println("Truncate Successful");
            System.out.println("Updating");
            Query = """
                    Insert into election
                    values
                    ();
                    """;
            statement.execute(Query);
            statement.close();
        }
        catch (SQLException E)
        {
            System.out.println("Truncate Error\n"+E);
        }
    }

    private void Result()
    {
        System.out.println("=============================================================================================================================================================");

        if(!Result)
        {
            System.out.println("Result is Not Open");
            return;
        }
        System.out.println("Role : President");
        Query = """
                Select s.name,s.dob,s.dept,s.batch ,c.email
                from canditates c
                Join students s
                on s.email = c.email
                where c.Post = "President"
                Order by c.Vote desc
                limit 1;""";
        student.showStudent(Query);

        System.out.println("\n\n=============================================================================================================================================================");
        System.out.println("Role : Vice President");
        Query = """
                Select s.name,s.dob,s.dept,s.batch ,c.email
                from canditates c
                Join students s
                on s.email = c.email
                where c.Post = "Vice President"
                Order by c.Vote desc
                limit 1;""";
        student.showStudent(Query);

        System.out.println("\n\n=============================================================================================================================================================");
        System.out.println("Role : Sports Captain");
        Query= """
                Select s.name,s.dob,s.dept,s.batch ,c.email
                from canditates c
                Join students s
                on s.email = c.email
                where c.Post = "Sports Captain"
                Order by c.Vote desc
                limit 1;""";
        student.showStudent(Query);
    }
    public boolean getLogin()
    {
        return login();
    }
}
