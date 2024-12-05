import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editBatch.html")
public class EditProductBatchServlet extends HttpServlet {
    @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/editBatch.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
      out.println(nextLine);
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
