import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editBatch.html")
public class EditBatchServlet extends HttpServlet {
    @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    // Get parameters
    String quantity = request.getParameter("quantity");
    String unit = request.getParameter("unit");
    String expirationDate = request.getParameter("expirationDate");
    String creationDate = request.getParameter("creationDate");
    String orderStatus = request.getParameter("orderStatus");
    String batchID = request.getParameter("batchID");
    boolean save = request.getParameter("save") != null;
    boolean remove = request.getParameter("remove") != null;
    boolean add = request.getParameter("add") != null;

      
    String itemID = request.getParameter("itemID");
    boolean product = itemID.substring(0,1).equals("P");
    int id = Integer.parseInt(itemID.substring(1));

    String message = verifyInput(quantity, unit, expirationDate, creationDate, orderStatus, save, remove, add, product, id, batchID);

    // Parameters we are going to use to populate page

    String name = "";
    ArrayList<Batch> batches;
    // If the item is a product
    if(product)
    {
      Product prod = DatabaseGetter.getProductFromID(id);
      name = prod.getName();
      batches = DatabaseGetter.getProductBatches(id);
    }
    // else if the item is an ingredient
    else
    {
      Ingredient ingredient = DatabaseGetter.getIngredientFromID(id);
      name = ingredient.getName();
      batches = DatabaseGetter.getIngredientBatches(id);
    }

    StringBuilder formattedInputs = new StringBuilder();

    for(int i = 0; i < batches.size(); i++)
    {
      Batch batch = batches.get(i);
      formattedInputs.append("{ quantity: '" + batch.getQuantity() + "', unit:'" + batch.getUnits() + "', expirationDate: '" + batch.getExpirationDate() + "' , creationDate: '" + batch.getCreationDate() + "', orderStatus: '" + batch.getStatus() + "', batchID: '#" + batch.getBatchNumber()  + "', saveButton: 'save'}");
      if(i != batches.size() - 1)
      {
        formattedInputs.append(",");
      }
    }

    
    
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/editBatch.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
      if(nextLine.contains("div class=\"head\""))
      {
        out.println("<div class=\"head\"> " + name + " </div>");
      }
      // Add message html
      else if(nextLine.contains("div class=\"message\""))
      {
          String color = "red";
          if(message.contains("successfully"))
          {
          color="green";
          }
          out.println("<div class=\"message\" style=\"color:" + color + "\">" + message + "</div>");
      }
      else if(nextLine.contains("div class=\"batchNum\""))
      {
        out.println("<div class=\"batchNum\"> Number of Batches: " + batches.size() + " </div>");
      }
      else if(nextLine.contains("const [formFields, setFormFields]"))
      {
        out.println("const [formFields, setFormFields] = useState(["+formattedInputs+"])");
      }
      else if(nextLine.contains("form class=\"box\""))
      {
        out.println(nextLine);
        out.println("<input type=\"hidden\" name=\"itemID\" value=\"" + itemID + "\"/>");
      }
      else
      {
        out.println(nextLine);
      }
      
    }
    System.out.println("Edit Batch");
    scan.close();
  }

  // Verifies the input from webapp and returns a message to display
  private String verifyInput(String quantity, String units, String expirationDate, String creationDate, String status, boolean save, boolean delete, boolean add, boolean product, int itemID, String batchID)
  {
    if(quantity == null && !delete)
    {
      return "";
    }
    if(delete)
    {
      if(batchID == null || batchID.equals(""))
      {
        return "Batch ID error";
      }
      // remove batchID associated with itemID
      if(product)
      {
        // Remove product batch
        DatabaseSetter.deleteProductBatch(Integer.parseInt(batchID));
      }
      else
      {
        // remove ingredient batch
        DatabaseSetter.deleteIngredientBatch(Integer.parseInt(batchID));
      }
      return "Batch removed successfully";
    }
      
    if(quantity.equals(""))
    {
      return "Missing Quantity";
    }
    if(units == null || units.equals(""))
    {
      return "Missing units.";
    }
    if(expirationDate == null || expirationDate.equals(""))
    {
      return "Missing Expiration Date.";
    }
    if(creationDate == null || creationDate.equals(""))
    {
      return "Missing Creation Date.";
    }
    if(creationDate == null || creationDate.equals(""))
    {
      return "Missing status.";
    }
    if(save)
    {
      if(batchID == null || batchID.equals(""))
      {
        return "Batch ID error";
      }
      if(product)
      {
        // Create product batch
        ProductBatch batch = new ProductBatch( Integer.parseInt(batchID), Float.parseFloat(quantity), units, creationDate, expirationDate, status, itemID);
        // Update product batch

      }
      else
      {
        // Create ingredient batch
        IngredientBatch batch = new IngredientBatch( Integer.parseInt(batchID), Float.parseFloat(quantity), units, creationDate, expirationDate, status, itemID);
        // Update ingredient batch

      }
      return "Batch updated successfully";
    }
    else if(add)
    {
      if(product)
      {
        // Create product batch
        ProductBatch batch = new ProductBatch(Float.parseFloat(quantity), units, creationDate, expirationDate, status, itemID);
        // Add product batch
        DatabaseSetter.insertProductBatch(batch);
      }
      else
      {
        // Create ingredient batch
        IngredientBatch batch = new IngredientBatch(Float.parseFloat(quantity), units, creationDate, expirationDate, status, itemID);
        // Add ingredient batch
        DatabaseSetter.insertIngredientBatch(batch);
      }
      return "Batch added successfully";
    }
    return "Something went wrong";
  }
}
