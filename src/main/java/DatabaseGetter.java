
import java.sql.*;

public class DatabaseGetter 
{

    //FILL IN YOUR MY SQL SERVER INFO HERE!!!! ==========

    //jbbc:mysql://localhost:[ENTERYOUR PORT NUMBER HERE]/[ENTER SCHEMA NAME HERE]
    static final String DB_URL = "jdbc:mysql://localhost:####/schemaNameHere";

    //should be using root so don't need to change
    static final String USER = "root";

     //If you put a password for the MySQL server put it here
    static final String PASSWORD = "12bucklemyshoes";
   
    //==============

    public static void main(String[] args)
    {

        try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            // INSERT QUERY FOR CARROT, TESTING
            statement.execute("INSERT IGNORE INTO ingredient Values(1, 'Carrot', 'Sourced from #farmnamehere', 'Keep refrigerated.')");

            // INSERT QUERY FOR STRAWBERRY, TESTING
            statement.execute("INSERT IGNORE INTO ingredient Values(2, 'Strawberry', 'Sourced from #farmnamehere ', 'Keep refrigerated.')");
                
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM ingredient");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                String resultInfo1 = results.getString("name");
                String resultInfo2 = results.getString("description");
                String resultInfo3 = results.getString("storageInstructions");

                //print info
                System.out.println(resultInfo1);
                System.out.println(resultInfo2);
                System.out.println(resultInfo3);
            } 

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

}