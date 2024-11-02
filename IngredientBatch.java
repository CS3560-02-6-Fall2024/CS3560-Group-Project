public class IngredientBatch extends Batch
{
    public int ingredientID;
    public int supplierID;
    public String dateAdded;

    // Constructor for IngredientBatch
    public IngredientBatch(int _ingredientID, int _supplierID)
    {
        ingredientID = _ingredientID;
        supplierID = _supplierID;
    }

    @Override
    public void removeFromBatch(int amount) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addToBatch(int amount) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}