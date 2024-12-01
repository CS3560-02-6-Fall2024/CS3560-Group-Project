public class Image
{
	private int productID;		// ID of the associated product
	private String imagePath;	// Path of the image starting from the root folder of the project repo
	private String description; // Short description of the image

	// Constructor for making a new Image
	public Image(int _productID, String _path, String _desc)
	{
		imagePath = _path;
		description = _desc;
	}

	// Update image description
	public void updateImage(String _desc)
	{
		// description = _desc
	}

	// Removes this image from it's associated product and deletes it
	// Returns true if deletion is sucessful, false otherwise
	public boolean deleteImage()
	{
		//destroy this
		throw new UnsupportedOperationException("Not supported yet.");
	}
}