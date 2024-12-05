import java.util.ArrayList;

public class SupplierBatch 
{
    private static int idCounter = 1; //used for pk

    //i am not used to new line bracklet lo
    private static ArrayList<SupplierBatch> everySupplierBatch = new ArrayList<>();

    private int supplierBatchId;
    private int supplierId;
    private int ingredientId;
    private float batchPrice;
    //expirationDate; do we need this?

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

    /**
     * Removes the given SupplierBatch from data
     * @param reqingredientId The ID of the SupplierBatch
     */
    public boolean removeSupplierBatch(int reqSupplierBatch)
    {
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED!!!");
    }

}
