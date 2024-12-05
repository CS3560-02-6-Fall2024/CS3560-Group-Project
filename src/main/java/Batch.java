import java.util.ArrayList;
public abstract class Batch
{
	public int batchNumber; // Key attribute used to identify the batch
	public int quantity; // Number of products in this batch
	public String units;
	public String expirationDate; // Date of expiration of ALL products in this batch (MM/DD/YYYY)

	// Removes from this batch's quantity after using "amount" number of items
	// Pseduo code
	// if quantity - amount < 0, we don't have enough products in this batch, search for other batches with this item
	// quantity = quantity - amount
	// if quantity = 0, remove/ notify to remove batch
	public abstract void removeFromBatch(int _amount);

	// Adds to this batch's quantity after using "amount" number of items
	// Used to fix batch information when a batch is incorrectly entered into the system
	public abstract void addToBatch(int _amount);

	// Delete this batch
	// Returns true if deletion is sucessful, false otherwise
	public abstract boolean deleteBatch(int _batchID);

	// Search for a batch by ingredientID
	// returns: a list of Batches that have that lookupID
	public abstract ArrayList<Batch> searchForBatch(int _lookupID);

}
