
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
   
    static Connection connection;
   

    // SINGLETON CONNETION
    private static Connection getConnection()
    {
        // If we already have a connection instance, return connection
        if(connection != null)
        {
            return connection;
        }


        // Otherwise, create a connection
        try 
        {
            connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            return connection;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //==============
    // Returns the ID of the last item in the item table
    public static int getLastItemID()
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Item");
            int id = -1;
            while(results.next())
            {
                id = results.getInt("itemID");
            }
            return id;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return -1;
        }
       
    }

    // Returns the ID of the recipe item in the item table
    public static int getLastRecipeIngredientID()
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM RecipeIngredient");
            int id = -1;
            while(results.next())
            {
                id = results.getInt("id");
            }
            return id;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return -1;
        }
       
    }

    public static int getLastSupplierID()
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Supplier");
            int id = -1;
            while(results.next())
            {
                id = results.getInt("supplierID");
            }
            return id;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return -1;
        }
       
    }

    // Returns the ID of the last batch in the batch table
    public static int getLastBatchNumber()
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Batch");
            int id = -1;
            while(results.next())
            {
                id = results.getInt("batchNumber");
            }
            return id;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return -1;
        }
       
    }

    public static int getItemIDFromName(String name)
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM item WHERE name = \"" + name + "\";");
            while(results.next())
            {
                return results.getInt("itemID");
            }
            return -1;
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
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Item");
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

    public static boolean checkForDuplicateSupplier(String name)
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Supplier");
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
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ArrayList<Product> products = new ArrayList<Product>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM Product INNER JOIN Item ON product.itemID=item.itemID;");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int productID = results.getInt("itemID");
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

    public static ArrayList<SupplierBatch> getSupplierBatches()
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ArrayList<SupplierBatch> batches = new ArrayList<SupplierBatch>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM SupplierBatch INNER JOIN Batch ON supplierBatch.batchNumber=batch.batchNumber;");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int batchNumber = results.getInt("batchNumber");
                float quantity= results.getFloat("quantity");
                String units = results.getString("units");
                String expirationDate= results.getString("expirationDate");
                int supplierID = results.getInt("supplierID");
                int itemID= results.getInt("itemID");
                float price = results.getFloat("batchPrice");
                batches.add(new SupplierBatch(batchNumber, quantity, units, expirationDate, supplierID, itemID, price));
            } 
            return batches;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    // Gets all batches from this ingredient
    public static ArrayList<Batch> getIngredientBatches(int ingredientID)
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ArrayList<Batch> batches = new ArrayList<Batch>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM IngredientBatch INNER JOIN Batch ON ingredientBatch.batchNumber=batch.batchNumber WHERE itemID = " + ingredientID + ";");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int batchNumber = results.getInt("batchNumber");
                float quantity= results.getFloat("quantity");
                String units = results.getString("units");
                String expirationDate= results.getString("expirationDate");
                String creationDate= results.getString("dateAdded");
                String status = results.getString("batchStatus");
                batches.add(new IngredientBatch(batchNumber, quantity, units, creationDate, expirationDate, status, ingredientID));
            } 
            return batches;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    // Gets all batches from this ingredient
    public static ArrayList<Batch> getProductBatches(int productID)
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ArrayList<Batch> batches = new ArrayList<Batch>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM ProductBatch INNER JOIN Batch ON productBatch.batchNumber=batch.batchNumber WHERE itemID = " + productID + ";");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int batchNumber = results.getInt("batchNumber");
                float quantity= results.getFloat("quantity");
                String units = results.getString("units");
                String expirationDate= results.getString("expirationDate");
                String creationDate= results.getString("dateAdded");
                String status = results.getString("batchStatus");
                batches.add(new ProductBatch(batchNumber, quantity, units, creationDate, expirationDate, status, productID));
            } 
            return batches;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    public static ArrayList<Ingredient> getIngredients()
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM Ingredient INNER JOIN Item ON Ingredient.itemID=item.itemID;");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int productID = results.getInt("itemID");
                String name= results.getString("name");
                String description= results.getString("description");
                String storageInstructions = results.getString("storageInstructions");
                ingredients.add(new Ingredient(productID, name, storageInstructions, description));
            } 
            return ingredients;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    // Gets recipe ingredients associated with ID
    public static ArrayList<RecipeIngredient> getRecipeIngredients(int productID)
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ArrayList<RecipeIngredient> ingredients = new ArrayList<RecipeIngredient>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM RecipeIngredient INNER JOIN Item ON item.itemID=RecipeIngredient.productID where item.itemID = " + productID + ";");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int id = results.getInt("id");
                int ingredientID = results.getInt("ingredientID");
                float quantity = results.getFloat("quantity");
                String units = results.getString("units");
                ingredients.add(new RecipeIngredient(id, productID, ingredientID, quantity, units));
            } 
            return ingredients;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    // Gets recipe ingredients names associated with productID
    public static ArrayList<String> getRecipeIngredientName(int productID)
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            // READ QUERY FOR ingredient, TESTING
            ArrayList<String> names = new ArrayList<String>();
            ResultSet results = statement.executeQuery("SELECT item.name FROM Item INNER JOIN RecipeIngredient ON item.itemID=RecipeIngredient.ingredientID WHERE RecipeIngredient.productID = " + productID);
            while (results.next())
            {
                names.add(results.getString("name"));
            } 
            return names;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    public static ArrayList<Supplier> getSuppliers()
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ArrayList<Supplier> allSuppliers = new ArrayList<>();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM supplier");
            while (results.next())
            {
                //get result info (reads the column name in get string)
                int id = results.getInt("supplierID");
                String name = results.getString("name");
                String phoneNumber = results.getString("phoneNumber");
                String email = results.getString("email");
                String address = results.getString("address");
                allSuppliers.add(new Supplier(id, name,phoneNumber,email,address));
            } 
            return allSuppliers;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static Product getProductFromID(int id)
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM product INNER JOIN Item ON product.itemID=item.itemID WHERE product.itemID=" + id +";");
            
            //System.out.println("IMAGE ROW: " + results.getRow() + " ID " + id + " IS BEFORE FIRST" + results.isBeforeFirst());
            
            // if the ResultSet is not empty then get the imagePath
            if(results.next())
            {
                //get result info (reads the column name in get string)
                int productID = results.getInt("itemID");
                String name= results.getString("name");
                String description= results.getString("description");
                float price = results.getFloat("standardPrice");
                return new Product(productID, name, description, price);
            }
            return null;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Ingredient getIngredientFromID(int id)
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM ingredient INNER JOIN Item ON ingredient.itemID=item.itemID WHERE ingredient.itemID=" + id +";");
            
            //System.out.println("IMAGE ROW: " + results.getRow() + " ID " + id + " IS BEFORE FIRST" + results.isBeforeFirst());
            
            // if the ResultSet is not empty then get the imagePath
            if(results.next())
            {
                //get result info (reads the column name in get string)
                int ingredientID = results.getInt("itemID");
                String name = results.getString("name");
                String description = results.getString("description");
                String storageInstructions = results.getString("storageInstructions");
                return new Ingredient(ingredientID, name, storageInstructions, description);
            }
            return null;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String getImageFromID(int id)
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM image WHERE itemID=" + id +";");
            
            //System.out.println("IMAGE ROW: " + results.getRow() + " ID " + id + " IS BEFORE FIRST" + results.isBeforeFirst());
            
            // if the ResultSet is not empty then get the imagePath
            if(results.next())
                return results.getString("imagePath");
                
            return "";
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return "";
        }
       
    }
    public static Supplier getSupplierFromID(int id)
    {
        try 
        {
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM supplier WHERE supplierID=" + id +";");
            
            //System.out.println("IMAGE ROW: " + results.getRow() + " ID " + id + " IS BEFORE FIRST" + results.isBeforeFirst());
            
            // if the ResultSet is not empty then get the imagePath
            if(results.next())
            {
                String name = results.getString("name");
                String phoneNumebr = results.getString("phoneNumber");
                String email = results.getString("email");
                String address = results.getString("address");

                return new Supplier(id, name, phoneNumebr, email, address);
            }
                
            return null;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
       
    }

    public static SupplierBatch getSuppilerBatchFromNumber(int batchNumber)
    {
        try
        {
            //get connector
           Connection connection = getConnection();
            Statement statement = connection.createStatement();
            // READ QUERY FOR ingredient, TESTING
            ResultSet results = statement.executeQuery("SELECT * FROM SupplierBatch INNER JOIN Batch ON supplierBatch.batchNumber=batch.batchNumber WHERE supplierBatch.batchNumber = " + batchNumber + ";");
            while (results.next())
            {
                int quantity= results.getInt("quantity");
                String units = results.getString("units");
                String expirationDate= results.getString("expirationDate");
                int supplierID = results.getInt("supplierID");
                int itemID= results.getInt("itemID");
                float price = results.getFloat("batchPrice");
                return new SupplierBatch(batchNumber, quantity, units, expirationDate, supplierID, itemID, price);
            } 
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }
}