import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order.html")
public class OrderServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    String orderNumber = request.getParameter("batchNumber");
    if(orderNumber != null)
    {
      int num = Integer.parseInt(orderNumber);
      DatabaseSetter.orderBatch(DatabaseGetter.getSuppilerBatchFromNumber(num));
      System.out.println("ordered batch");
    }


    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/order.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
      if(nextLine.contains("START RESULT BOX"))
      {
        // Skip past this result box section
        while(scan.hasNextLine())
        {
          nextLine = scan.nextLine();
          // Break out of result box loop if we reach the end of the result box
          if(nextLine.contains("END RESULT BOX"))
            break;
        }
        
        ArrayList<SupplierBatch> batches = DatabaseGetter.getSupplierBatches();
        for (SupplierBatch supplierBatch : batches) 
        {
          Ingredient ing = DatabaseGetter.getIngredientFromID(supplierBatch.getIngredientId());
          String image = DatabaseGetter.getImageFromID(supplierBatch.getIngredientId());
          Supplier supplier = DatabaseGetter.getSupplierFromID(supplierBatch.getSupplierId());
          System.out.println("Result box html start");
          String resultBoxHtml = "<div class=\"result-box\">\r\n" + //
                        "                    <div class=\"product-ID\"> $" + supplierBatch.getBatchPrice() + "</div>\r\n" + //
                        "                    <img src=\"Images/" + image + "\" alt=\"No Image\">\r\n" + //
                        "                    <div class=\"ingredient-name\"> " + supplierBatch.getQuantity() + " " + supplierBatch.getUnits() + " " + ing.getName() + " </div>\r\n" + //
                        "                    <div class=\"supplier-name\">Supplier Name: " + supplier.getName() + "</div>\r\n" + //
                        "                    <div class=\"buttons\">\r\n" + //
                        "                        <button onclick=\"location.href='order.html?batchNumber="+supplierBatch.getBatchNumber()+"'\" class=\"edit-button\">Order</button><br/>\r\n" + //
                        "                    </div>               \r\n" + //
                        "                </div>";
          out.println(resultBoxHtml);
        }
      }
      out.println(nextLine);
    }
    scan.close();
    
  }
}

