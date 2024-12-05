
import java.util.ArrayList;

public class ProductBatch extends Batch
{
    public int productID;

    // Constructor for IngredientBatch
    public ProductBatch(float quantity, String units, String dateAdded, String expirationDate, String status, int productID)
    {
        batchNumber = DatabaseGetter.getLastBatchNumber() + 1;
        this.quantity= quantity;
        this.units=units;
        this.expirationDate = expirationDate;
        this.creationDate = dateAdded;
        this.expirationDate = expirationDate;
        this.status = status;
        this.productID = productID;
    }
    public ProductBatch(int batchNumber, float quantity, String units, String dateAdded, String expirationDate, String status, int productID)
    {
        this.batchNumber = batchNumber;
        this.quantity= quantity;
        this.units=units;
        this.expirationDate = expirationDate;
        this.creationDate = dateAdded;
        this.expirationDate = expirationDate;
        this.status = status;
        this.productID = productID;
    }

    // IAbstract methods
    @Override
    public void removeFromBatch(int _amount) 
    {
        // int remainingQuanity = quantity - _amount;

        // if (remainingQuanity < 0) //if the current batch doesn't have enough
        // {
        //     quantity = 0; //set own quantity to 0
            
          
            
        // }
        // else //if you can take away and it still be avalible
        // {
        //     quantity -= _amount;
        // }
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

    // Getters
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

    
    public int getProductID()
    {
        return productID;
    }
}