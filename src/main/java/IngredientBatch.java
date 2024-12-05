
import java.util.ArrayList;

public class IngredientBatch extends Batch
{
    public static int ingredientID;
    public int supplierID;
    public String dateAdded;

    // Constructor for IngredientBatch
    public IngredientBatch(int _ingredientID, int _supplierID, String _dateAdded)
    {
        ingredientID = _ingredientID;
        ingredientID++;
        supplierID = _supplierID;
        dateAdded = _dateAdded;
    }

    // returns true if the quantity of this batch is below the reorder threshold
    // returns false otherwise
    public boolean isReadyForReorder()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // returns true if the product is Expired
    // returns false otherwise
    public boolean isExpired()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getIngredientId()
    {
        return ingredientID;
    }

    // IAbstract methods
    @Override
    public void removeFromBatch(int _amount) 
    {
        int remainingQuanity = quantity - _amount;

        if (remainingQuanity < 0) //if the current batch doesn't have enough
        {
            quantity = 0; //set own quantity to 0
            
          
            
        }
        else //if you can take away and it still be avalible
        {
            quantity -= _amount;
        }
    }

    @Override
    public void addToBatch(int _amount) 
    {
        if (_amount > 0)
            quantity += _amount;
    }

    @Override
    public boolean deleteBatch(int _batchID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Batch> searchForBatch(int _ingredientID) {
        throw new UnsupportedOperationException("So how does this work o_");
        /*
        ArrayList<Batch> returnArray = new ArrayList<>();

        for (SupplierBatch supplierBatch : everySupplierBatch)
        {
            if (supplierBatch.getIngredientId() == _lookupID)
                returnArray.add(supplierBatch);
        }

        return returnArray;
        */
    }
}