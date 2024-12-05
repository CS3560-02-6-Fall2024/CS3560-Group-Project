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
	
    String itemID = request.getParameter("itemID");
    boolean product = itemID.substring(0,1).equals("P");
    int id = Integer.parseInt(itemID.substring(1));

    // Parameters we are going to use to populate page

    String name = "";
    String description = "";
    ArrayList<Batch> batches;
    // If the item is a product
    if(product)
    {
      Product prod = DatabaseGetter.getProductFromID(id);
      name = prod.getName();
      description = prod.getDescription();
      batches = new ArrayList<Batch>();
    }
    // else if the item is an ingredient
    else
    {
      Ingredient ingredient = DatabaseGetter.getIngredientFromID(id);
      name = ingredient.getName();
      description = ingredient.getDescription();
      batches = DatabaseGetter.getIngredientBatches(id);
    }

    StringBuilder formattedInputs = new StringBuilder();

    for(int i = 0; i < batches.size(); i++)
    {
      Batch batch = batches.get(i);
      formattedInputs.append("{ quantity: '" + batch.getQuantity() + "', unit:'" + batch.getUnits() + "', expirationDate: '" + batch.getExpirationDate() + "' , creationDate: '" + batch.getCreationDate() + "', orderStatus: '" + batch.getStatus() + "', batchID: '#" + batch.getBatchNumber() + "'}");
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
      else if(nextLine.contains("div class=\"batchNum\""))
      {
        out.println("<div class=\"batchNum\"> Number of Batches: " + batches.size() + " </div>");
      }
      else if(nextLine.contains("const [formFields, setFormFields]"))
      {
        out.println("const [formFields, setFormFields] = useState(["+formattedInputs+"])");
      }
      else if(nextLine.contains("<div class=\"App\">"))
      {
        out.println(nextLine);
      }
      else
      {
        out.println(nextLine);
      }
      
    }
    System.out.println("Edit Batch");
    scan.close();
  }

  

  // @Override
  // public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
  //   String action = request.getParameter("action");

  //   if ("add".equals(action)) {
  //     // Add a new batch
  //     String batchName = request.getParameter("batchName");
  //     String batchSize = request.getParameter("batchSize");
  //     String productionDate = request.getParameter("productionDate");
  //     String expiryDate = request.getParameter("expiryDate");

  //     String message = addBatch(batchName, batchSize, productionDate, expiryDate);
  //     response.getWriter().write(message);

  //   } else if ("remove".equals(action)) {
        
  //     // Remove an existing batch
  //     String batchID = request.getParameter("batchID");

  //     String message = removeBatch(Integer.parseInt(batchID));
  //     response.getWriter().write(message);
  //   }
  // }

  // // Add a new batch to the database
  // private String addBatch(String batchName, String batchSize, String productionDate, String expiryDate) {
  //   if (batchName == null || batchName.isEmpty()) {
  //     return "Missing batch name.";
  //   }
  //   if (batchSize == null || batchSize.isEmpty()) {
  //     return "Missing batch size.";
  //   }
  //   try {
  //     int size = Integer.parseInt(batchSize);
  //     if (size <= 0) {
  //       return "Batch size must be positive.";
  //     }
  //   } catch (NumberFormatException e) {
  //     return "Batch size must be a number.";
  //   }
  //   if (productionDate == null || productionDate.isEmpty()) {
  //     return "Missing production date.";
  //   }
  //   if (expiryDate == null || expiryDate.isEmpty()) {
  //     return "Missing expiry date.";
  //   }

  //   Batch batch = new Batch(batchName, Integer.parseInt(batchSize), productionDate, expiryDate);
  //   DatabaseSetter.insertBatch(batch);
  //   return "Batch added successfully.";
  // }

  // // Remove a batch from the database
  // private String removeBatch(int batchID) {
  //   boolean success = DatabaseSetter.deleteBatch(batchID);
  //   if (success) {
  //     return "Batch removed successfully.";
  //   } else {
  //     return "Failed to remove batch.";
  //   }
  // }
}
