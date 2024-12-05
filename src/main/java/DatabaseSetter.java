
import java.sql.*;
import java.util.ArrayList;

public class DatabaseSetter 
{

    //FILL IN YOUR MY SQL SERVER INFO HERE!!!! ==========

    //jbbc:mysql://localhost:[ENTERYOUR PORT NUMBER HERE]/[ENTER SCHEMA NAME HERE]
    static final String DB_URL = "jdbc:mysql://localhost:3306/groupassignment";

    //should be using root so don't need to change
    static final String USER = "root";

     //If you put a password for the MySQL server put it here
    static final String PASSWORD = "12bucklemyshoe";
   
    // INSERT METHODS
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

    // UPDATE METHODS
    public static void updateProduct(Product product)
	{
		System.out.println("Update product into database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            // Insert query for Item
            statement.execute("UPDATE Item SET name = '" + product.getName() + "', description = '" + product.getDescription() + "' WHERE itemID = " + product.getItemID() + ";");

            // Insert query for product
			statement.execute("UPDATE Product SET standardPrice = " + product.getPrice() + " WHERE itemID = " + product.getItemID() + ";");

        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

    public static void updateRecipe(Product product, ArrayList<RecipeIngredient> ingredients)
	{
		System.out.println("Update recipe into database...");
        deleteRecipeIngredient(product.getItemID());

        for (RecipeIngredient recipeIngredient : ingredients) 
        {
            insertRecipeIngredient(recipeIngredient);
        }
	}

    public static void updateImage(Image image)
	{
		System.out.println("Update image into database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            // Insert query for Item
            // if image is null, insert image
            DatabaseSetter.insertImage(image);
            
            statement.execute("UPDATE Image SET itemID = '" + image.getItemID() + "', imagePath = '" + image.getPath() + "' WHERE itemID = " + image.getItemID() + ";");

        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

    // DELETE METHODS
    public static void deleteProduct(Product product)
	{
		System.out.println("Delete product from database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            // Delete query for recipeIngredient
            statement.execute("DELETE FROM recipeIngredient WHERE productID = " + product.getItemID() + ";");

            // Delete query for image
            statement.execute("DELETE FROM image WHERE itemID = " + product.getItemID() + ";");

            // Delete query for product
			statement.execute("DELETE FROM product WHERE itemID = " + product.getItemID() + ";");

            // Delete query for Item
            statement.execute("DELETE FROM item WHERE itemID = " + product.getItemID() + ";");

            


        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
    public static void deleteRecipeIngredient(int productID)
	{
		System.out.println("Delete product from database...");
		try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement statement = connection.createStatement();

            // Delete query for recipeIngredient
            statement.execute("DELETE FROM recipeIngredient WHERE productID = " + productID + ";");
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
}