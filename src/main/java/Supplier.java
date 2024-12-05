import java.util.ArrayList;

public class Supplier
{
	private int supplierID; // Primary key for suppliers
	private String name; // Name of the supplier
	private String phoneNumber; // Number of the supplier 
	private String email; // Email of the supplier
	private String address; // Information user wants to note down, such as ingredients sold 

	// Constructor for Supplier
	public Supplier(String _name, String _phoneNumber, String _email, String address)
	{
		this.supplierID = DatabaseGetter.getLastSupplierID() + 1;
		this.name = _name;
		this.phoneNumber = _phoneNumber;
		this.email = _email;
		this.address = address;
	}

	// Constructor for Supplier
	public Supplier(int id, String _name, String _phoneNumber, String _email, String address)
	{
		this.supplierID = id;
		this.name = _name;
		this.phoneNumber = _phoneNumber;
		this.email = _email;
		this.address = address;
	}

	// Getter methods
	public int getID()
	{
		return supplierID;
	}
	public String getName()
	{
		return name;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	public String getEmail()
	{
		return email;
	}
	public String getAddress()
	{
		return address;
	}

	// Method to Remove Supplier
	public boolean removeSupplier(int id)
	{
		// For loop that loops through supplier list 
		// If-else loop to match by supplier ID 
		// Return true if successfully removed, false if not
		throw new UnsupportedOperationException("Not supported yet.");
	}

	// Method to Search for Suppler (by name)
	public static Supplier searchSupplier(String name) 
	{
		// For loop that loops through supplier list
		// If-else loop to match by name
		// If found, return supplier, if not, return null
		throw new UnsupportedOperationException("Not supported yet.");
	}

	// Method to Display Supplier Information
	public void displaySupplierInfo()
	{
		// Print headings for each attribute on a new line
		// Ex: "Name: "
		throw new UnsupportedOperationException("Not supported yet.");
	}	
}
