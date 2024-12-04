import java.util.ArrayList;
public class Ingredient extends Item
{
	private String storageIntructions;
	private int totalQuantity; // Amount of this ingredient that we have (All batches of an item combined)

	// Constructor for Ingredient
	public Ingredient(String name, String storageInstructions, String description)
	{
		this.itemID = DatabaseGetter.getLastItemID() + 1;
		this.name = name;
		this.storageIntructions = storageInstructions;
		this.description = description;
	}

	// Constructor for Ingredient that exists in database
	public Ingredient(int id, String name, String storageInstructions, String description)
	{
		this.itemID = id;
		this.name = name;
		this.storageIntructions = storageInstructions;
		this.description = description;
	}

	// Method to Remove Ingredient
	public static boolean removeIngredient(int ingredientID)
	{
		// Loop through ingredients list
		// If statement to match ID 
		// If successful, return true, if not, return false
		throw new UnsupportedOperationException("Not supported yet.");
	}

	// Method to Search for Ingredient
	public static Ingredient searchIngredient(String name)
	{
		// Loop through ingredients list 
		// If statement to match name 
		// If successful, return ingredient, if not, return null
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	// Method to Display Ingredient
	public void displayInfo()
	{
		// Print headers to each attribute
		// Will also print corresponding batch information 
		throw new UnsupportedOperationException("Not supported yet.");
	}

	// Getter methods
	public int getItemID()
	{
		return itemID;
	}

	public String getName()
	{
		return name;
	}

	public String getStorageIntructions() {
		return storageIntructions;
	}

	public String getDescription()
	{
		return description;
	}
}
