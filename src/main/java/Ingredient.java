import java.util.ArrayList;
public class Ingredient
{
	private int ingredientID;
	private String name; // Name of the ingredient
	private String storageIntructions;
	private String description; // Short description of the ingredient
	private int totalQuantity; // Amount of this ingredient that we have (All batches of an item combined)

	// Constructor for Ingredient
	public Ingredient(String name, String storageInstructions, String description)
	{
		this.ingredientID = DatabaseGetter.getLastIngredientID() + 1;
		this.name = name;
		this.storageIntructions = storageInstructions;
		this.description = description;
	}

	// Update Methods
	public void updateName(String newName)
	{
		this.name = newName;
	}

	public void updateDescription(String newDescription)
	{
		this.description = newDescription;
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
	public int getIngredientID()
	{
		return ingredientID;
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
