public class Product
{
	private int productID;
	private String name; // The display name of the product
	private String description; // Short description of the product
	private int totalQuantity; // Amount of this product in the order?


	// Constructor for Product
	public Product(int _productID, String _name, String _desc)
	{
		productID = _productID;
		name = _name;
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
}