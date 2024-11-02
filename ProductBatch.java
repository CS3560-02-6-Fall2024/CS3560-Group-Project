public class ProductBatch extends Batch
{
    public int productID;
    public String dateAdded;

    // Constructor for ProductBatch
    public ProductBatch(int _productID)
    {
        productID = _productID;
    }

     @Override
    public void removeFromBatch(int _amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addToBatch(int _amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteBatch(int _batchID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}