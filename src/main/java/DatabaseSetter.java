
import java.sql.*;

public class DatabaseSetter 
{

    //FILL IN YOUR MY SQL SERVER INFO HERE!!!! ==========

    //jbbc:mysql://localhost:[ENTERYOUR PORT NUMBER HERE]/[ENTER SCHEMA NAME HERE]
    static final String DB_URL = "jdbc:mysql://localhost:3306/groupassignment";

    //should be using root so don't need to change
    static final String USER = "root";

     //If you put a password for the MySQL server put it here
    static final String PASSWORD = "12bucklemyshoe";
   
    public static void insertImage(Image image)
    {
        System.out.println("Adding image into database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            // Insert query for product
			statement.execute("INSERT IGNORE INTO image Values(" + 
			image.getProductID() + ", '" + image.getPath() + "')");
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


	public static void insertProduct(Product product)
	{
		System.out.println("Adding product into database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            // Insert query for product
			statement.execute("INSERT IGNORE INTO product Values(" + 
			product.getProductID() + ", " + 
			"'" + product.getName() + "', 0, " + 
			"'" + product.getDescription() + "', " + product.getPrice() + ")");
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

}