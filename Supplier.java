import.java.util.*;

public class Supplier
{
	private static int idCounter = 1; // Counter to ensure unique supplier IDs
	private static List<Supplier> suppliers = new ArrayList<>(); // List of all suppliers
	
	private int supplierID; // Primary key for suppliers
	private String name; // Name of the supplier
	private String phoneNumber; // Number of the supplier 
	private String email; // Email of the supplier
	private String description; // Information user wants to note down, such as ingredients sold 

	// Constructor for Supplier
	public Supplier(String _name, String _phoneNumber, String _email, String _description)
	{
		this.supplierID = idCounter++;
		this.name = _name;
		this.phoneNumber = _phoneNumber;
		this.email = _email;
		this.description = _description;
		suppliers.add(this);
	}

	// Individual Update Methods 
	public void updateName(String newName) 
	{
		this.name = newName;
	}

	public void updateNumber(String newNumber) 
	{
		this.phoneNumber = newNumber;
	}

	public void updateEmail(String newEmail) 
	{
		this.email = newEmail;
	}
	
	public void updateDescription(String newDescription)
	{
		this.description = newDescription;
	}

	// Method to Remove Supplier
	public boolean removeSupplier(String name)
	{
		// For loop that loops through supplier list 
		// If-else loop to match by name 
		// Return true if successfully removed, false if not
	}

	// Method to Search for Suppler (by name)
	public static Supplier searchSupplier(String name) 
	{
		// For loop that loops through supplier list
		// If-else loop to match by name
		// If found, return supplier, if not, return null
	}

	// Method to Display Supplier Information
	public void displaySupplierInfo()
	{
		// Print headings for each attribute on a new line
		// Ex: "Name: "
	}

	// Getters
	
		
}
