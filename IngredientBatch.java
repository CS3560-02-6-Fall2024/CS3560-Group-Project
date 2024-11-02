
import java.util.ArrayList;

public class IngredientBatch extends Batch
{
    public static int ingredientID;
    public int supplierID;
    public String dateAdded;

    // Constructor for IngredientBatch
    public IngredientBatch(int _ingredientID, int _supplierID)
    {
        ingredientID = _ingredientID;
        ingredientID++;
        supplierID = _supplierID;
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

    // Implement abstract methods
    @Override
    public void removeFromBatch(int _amount) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addToBatch(int _amount) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteBatch(int _batchID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Batch> searchForBatch(int _ingredientID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}