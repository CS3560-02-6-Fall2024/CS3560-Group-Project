import java.util.ArrayList;

public class SupplierBatch extends Batch
{
    
    public static ArrayList<SupplierBatch> everySupplierBatch = new ArrayList<>();
    public static int idCounter = 1;
    private int supplierBatchId;
    private int supplierId;
    private int ingredientId;
    private float batchPrice;


    /**
     * Make new SupplierBatch
     * @param _supplierId The Supplier ID
     * @param _ingredientId The Ingredient ID
     */
    public SupplierBatch(int _supplierId, int _ingredientId, float _batchPrice)
    {
        supplierBatchId = idCounter++;
        supplierId = _supplierId;
        ingredientId = _ingredientId;
        batchPrice = _batchPrice;
        everySupplierBatch.add(this);
       
    }
    
    /**
     * Returns the Supplier ID
     * @return The Supplier ID
     */
    public int getSupplierId()
    {
        return supplierId;
    }

    /**
     * Returns the Ingredient ID
     * @return The ID of ingredient
     */
    public int getIngredientId()
    {
        return ingredientId;
    }

    /**
     * Returns the price of the supplier batch
     * @return Price of the supplierBatch
     */
    public float getBatchPrice(){
        return batchPrice;
    }

    
    //Abstract methods

	// Removes from this batch's quantity after using "amount" number of items
	// Pseduo code
	// if quantity - amount < 0, we don't have enough products in this batch, search for other batches with this item
	// quantity = quantity - amount
	// if quantity = 0, remove/ notify to remove batch
    @Override
	public void removeFromBatch(int _amount)
    {
        throw new UnsupportedOperationException();
    }

	// Adds to this batch's quantity after using "amount" number of items
	// Used to fix batch information when a batch is incorrectly entered into the system
    @Override
	public void addToBatch(int _amount)
    {

        throw new UnsupportedOperationException();
    };

	// Delete this batch
	// Returns true if deletion is sucessful, false otherwise
    @Override
	public boolean deleteBatch(int _batchID)
    {
        throw new UnsupportedOperationException();
    }

	// Search for a batch by ingredientID
	// returns: a list of Batches that have that lookupID
    @Override
	public ArrayList<Batch> searchForBatch(int _lookupID)
    {
        throw new UnsupportedOperationException();
    }

}
