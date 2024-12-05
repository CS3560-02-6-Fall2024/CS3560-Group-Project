
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
			image.getItemID() + ", '" + image.getPath() + "')");
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

            // Insert query for Item
            statement.execute("INSERT IGNORE INTO Item Values(" + 
			product.getItemID() + ", " + 
			"'" + product.getName() + "', " + 
			"'" + product.getDescription() + "')");

            // Insert query for product
			statement.execute("INSERT IGNORE INTO Product Values(" + 
			product.getItemID() + ", " + product.getPrice() + ")");


        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

    public static void insertIngredient(Ingredient ingredient)
	{
		System.out.println("Adding ingredient into database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();


            // Insert query for item
            statement.execute("INSERT IGNORE INTO Item Values(" + 
			ingredient.getItemID() + ", " + 
			"'" + ingredient.getName() + "', " + 
			"'" + ingredient.getDescription() + "')");

            // Insert query for product
			statement.execute("INSERT IGNORE INTO Ingredient Values(" + 
			ingredient.getItemID() + ", '" + ingredient.getStorageIntructions() + "')");


        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

    public static void insertRecipeIngredient(RecipeIngredient ingredient)
	{
		System.out.println("Adding ingredient into database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();


            // Insert query for RecipeIngredient
            statement.execute("INSERT IGNORE INTO RecipeIngredient Values(" + 
			ingredient.getID() + ", " + ingredient.getProductID() + ", " + ingredient.getIngredientID() + 
            ", " + ingredient.getQuantity() + ", '" + ingredient.getUnits() + "')"); 


        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

}