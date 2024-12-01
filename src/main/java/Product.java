public class Product
{
	private int productID;
	private String name; // The display name of the product
	private String description; // Short description of the product
	private float price;
	private int totalQuantity; // Amount of this product in the order?


	// Constructor for Product
	public Product(String _name, String _desc, float _price)
	{
		this(DatabaseGetter.getLastProductID() + 1, _name, _desc, _price);
	}

	public Product(int id, String _name, String _desc, float _price)
	{
		productID = id;
		name = _name;
		price = _price;
		description = _desc;
	}

	// Updates product name and description
	public void updateProduct(String _name, String _desc)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	// Removes this product and deletes all related batches from system
	// Returns true if deletion is sucessful, false otherwise
	public boolean deleteProduct()
	{
		//destroy this
		//destroy related batches
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	// Method to Search for Product
	public static Product searchProduct(String name)
	{
		// Loop through product list 
		// If statement to match name 
		// If successful, return ingredient, if not, return null
		throw new UnsupportedOperationException("Not supported yet.");
	}

	// Getter methods
	public int getProductID()
	{
		return productID;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public float getPrice()
	{
		return price;
	}

	
}