
import java.sql.*;
import java.util.ArrayList;

public class DatabaseGetter 
{

    //FILL IN YOUR MY SQL SERVER INFO HERE!!!! ==========

    //jbbc:mysql://localhost:[ENTERYOUR PORT NUMBER HERE]/[ENTER SCHEMA NAME HERE]
    static final String DB_URL = "jdbc:mysql://localhost:3306/groupassignment";

    //should be using root so don't need to change
    static final String USER = "root";

     //If you put a password for the MySQL server put it here
    static final String PASSWORD = "12bucklemyshoe";
   
    //==============


    public static int getLastProductID()
    {
        try 
        {
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM product");
            int id = -1;
            while(results.next())
            {
                id = results.getInt("productID");
            }
            return id;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return -1;
        }
       
    }


    // Return's true if there is no duplicates
    // False otherwise
    public static boolean checkForDuplicateProducts(String name)
    {
        try 
        {
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM product");
            while(results.next())
            {
                String n = results.getString("name");
                if(name.equalsIgnoreCase(n))
                    return false;
            }
            return true;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Product> getProducts()
    {
        try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();
            ArrayList<Product> products = new ArrayList<Product>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM product");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int productID = results.getInt("productID");
                String name= results.getString("name");
                String description= results.getString("description");
                float price = results.getFloat("standardPrice");
                products.add(new Product(productID, name, description, price));
            } 
            return products;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    public static void testDatabaseGetter()
    {
        System.out.println("Testing Database...");
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