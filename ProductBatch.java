
import java.util.ArrayList;

public class ProductBatch extends Batch
{
    public int productID;
    public String dateAdded;

    // Constructor for ProductBatch
    public ProductBatch(int _productID)
    {
        productID = _productID;
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

    @Override
    public ArrayList<Batch> searchForBatch(int _productID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}