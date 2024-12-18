import java.util.ArrayList;

public class SupplierBatch extends Batch
{
    private int supplierId;
    private int ingredientId;
    private float batchPrice;


    /**
     * Make new SupplierBatch
     * @param _supplierId The Supplier ID
     * @param _ingredientId The Ingredient ID
     */
    public SupplierBatch(float quantity, String units, String expirationDate, int _supplierId, int _ingredientId, float _batchPrice)
    {
        batchNumber = DatabaseGetter.getLastBatchNumber() + 1;
        this.quantity= quantity;
        this.units=units;
        this.expirationDate = expirationDate;
        supplierId = _supplierId;
        ingredientId = _ingredientId;
        batchPrice = _batchPrice;
       
    }

    public SupplierBatch(int batchNumber, float quantity, String units, String expirationDate, int _supplierId, int _ingredientId, float _batchPrice)
    {
        this.batchNumber = batchNumber;
        this.quantity= quantity;
        this.units=units;
        this.expirationDate = expirationDate;
        supplierId = _supplierId;
        ingredientId = _ingredientId;
        batchPrice = _batchPrice;
       
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

    public float getQuantity(){
        return quantity;
    }

    public int getBatchNumber()
    {
        return batchNumber;
    }

    public String getUnits()
    {
        return units;
    }

    public String getCreationDate() {
        return creationDate;
    }


    public String getExpirationDate() {
        return expirationDate;
    }

    public String getStatus() {
        return status;
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
        // int remainingQuanity = quantity - _amount;

        // if (remainingQuanity < 0) //if the current batch doesn't have enough
        // {
        //     quantity = 0; //set own quantity to 0
        //     int ingredientToRemove = this.getIngredientId();

        //     for (SupplierBatch thisSupplierBatch : everySupplierBatch)
        //     {
        //         // take away the batch amount from this if its the same ingredientID
        //         // and has stuff to remove
        //         int thisBatchQuantity = thisSupplierBatch.getQuantity();

        //         if ((thisBatchQuantity > 0) &&
        //             thisSupplierBatch.getIngredientId() == ingredientToRemove)
        //         {
                    
        //             int amountToTake = remainingQuanity - thisBatchQuantity;

        //             if (amountToTake <= 0)
        //             {
        //                 thisSupplierBatch.removeFromBatch(thisBatchQuantity);
        //                 remainingQuanity = amountToTake;
        //             }
        //             else //if you can take the amount shown
        //             {
        //                 thisSupplierBatch.removeFromBatch(amountToTake);
        //                 remainingQuanity = 0;
        //             }
        //             thisSupplierBatch.removeFromBatch(remainingQuanity);

        //         }
        //     }
        // }
        // else //if you can take away and it still be avalible
        // {
        //     quantity -= _amount;
        // }
        
    }

	// Adds to this batch's quantity after using "amount" number of items
	// Used to fix batch information when a batch is incorrectly entered into the system
    @Override
	public void addToBatch(int _amount)
    {
        if (_amount > 0)
            quantity += _amount;
    };

	// Delete this batch
	// Returns true if deletion is sucessful, false otherwise
    @Override
	public boolean deleteBatch(int _batchID)
    {
    //    boolean hasDeletedID = false;
    //     for (SupplierBatch supplierBatch : everySupplierBatch)
    //     {
    //         if (supplierBatch.getSupplierBatchId() == _batchID)
    //         {
    //             everySupplierBatch.remove(supplierBatch);
    //             break;
    //         }
    //     }

        // return hasDeletedID;
        return false;
        
    }

	// Search for a batch by ingredientID
	// returns: a list of Batches that have that lookupID
    @Override
	public ArrayList<Batch> searchForBatch(int _lookupID)
    {
        ArrayList<Batch> returnArray = new ArrayList<>();

        // for (SupplierBatch supplierBatch : everySupplierBatch)
        // {
        //     if (supplierBatch.getIngredientId() == _lookupID)
        //         returnArray.add(supplierBatch);
        // }

        return returnArray;
    }

    
}
