public class Ingredient
{
	private static int idCounter = 1; // Counter for unique IDs
	private static List<Ingredient> ingredients = new ArrayList<>(); // List of Ingredients

	private int ingredientID;
	private String name; // Name of the ingredient
	private String description; // Short description of the ingredient
	private int totalQuantity; // Amount of this ingredient that we have (All batches of an item combined)

	// Constructor for Ingredient
	public Ingredient(String name, String description)
	{
		this.ingredientID = idCounter++;
		this.name = name;
		this.description = description;
		this.totalQuantity = 0;
		ingredients.add(this);
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
	}

	// Method to Searh for Ingredient
	public static Ingredient searchingredient(String name)
	{
		// Loop through ingredients list 
		// If statemtne to match name 
		// IF successfull, return ingredient, if not, return null
	}
	
	// Method to Display Ingredient
	public void displayInfo()
	{
		// Print headers to each attribute
		// Will also print corresponding batch information 
	}

	

	
}
