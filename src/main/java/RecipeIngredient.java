import java.util.ArrayList;
public class RecipeIngredient
{
	private int id, productID, ingredientID;
	private float quantity;
	private String units; // Amount of this ingredient that we have (All batches of an item combined)

	// Constructor for Ingredient
	public RecipeIngredient(int productID, int ingredientID, float quantity, String units)
	{
		this.id = DatabaseGetter.getLastRecipeIngredientID() + 1;
		this.productID = productID;
		this.ingredientID = ingredientID;
		this.quantity = quantity;
		this.units = units;
	}
	// Constructor for Ingredient
	public RecipeIngredient(int id, int productID, int ingredientID, float quantity, String units)
	{
		this.id = id;
		this.productID = productID;
		this.ingredientID = ingredientID;
		this.quantity = quantity;
		this.units = units;
	}

	// Creates a new row for this recipe ingredient
	public static void CreateRecipeIngredient(RecipeIngredient ingredient)
	{
		DatabaseSetter.insertRecipeIngredient(ingredient);
	}

	// Gets recipe ingredients associated with productID
	public static ArrayList<RecipeIngredient> GetRecipeIngredients(int productID)
	{
		return DatabaseGetter.getRecipeIngredients(productID);
	}

	// Getter methods
	public int getID()
	{
		return id;
	}

	public int getProductID()
	{
		return productID;
	}

	public int getIngredientID()
	{
		return ingredientID;
	}

	public float getQuantity() {
		return quantity;
	}

	public String getUnits()
	{
		return units;
	}
}
