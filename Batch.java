public class Batch
{
	private int batchNumber; // Key attribute used to identify the batch
	private int quantity; // Number of products in this batch
	private String expirationDate; // Date of expiration of ALL products in this batch (MM/DD/YYYY)


	// Removes from this batch's quantity after using "amount" number of items
	private void removeFromBatch(int amount)
	{
		// if quantity - amount < 0, we don't have enough products in this batch, search for other batches with this item
		// quantity = quantity - amount
		// if quantity = 0, remove/ notify to remove batch
	}

	// Adds to this batch's quantity after using "amount" number of items
	// Used to fix batch information when a batch is incorrectly entered into the system
	private void addToBatch(int amount)
	{
		// quantity = quantity + amount
	}

}