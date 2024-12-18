import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search.html")
public class SearchServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {

    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/search.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();

      // Copy the result box html so we can to populate page
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
        // DatabaseSetter.addProduct(new Product(1, "Pancake", "Better than a waffle?"));
        ArrayList<Product> products = DatabaseGetter.getProducts();
        // Add results box n times into the page
        ArrayList<Ingredient> ings = DatabaseGetter.getIngredients();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ings.size(); i++) 
        {
          str.append("ingredientList=" + ings.get(i).getName());
          if(i != ings.size() - 1)
          {
            str.append("&");
          }
        }
        for(Product product : products)
        {
          ArrayList<Batch> batches = DatabaseGetter.getProductBatches(product.getItemID());
          String resultBoxHtml = "<div class=\"result-box\">\r\n" + //
          "                    <div class=\"product-ID\"> #P" + product.getItemID() + "</div>\r\n" + //
          "                    <div class=\"image\"><img src=\"Images/" + DatabaseGetter.getImageFromID(product.getItemID()) + "\" alt=\"No Image\"></div>\r\n" + //
          "                    <div class=\"product-name\">" + product.getName() + "</div>\r\n" + //
          "                    <div class=\"batch-quantity\"> Number of batches: " + batches.size() + "  </div>\r\n" + //
          "                    <div class=\"buttons\">\r\n" + //
          "                        <button onclick=\"location.href='editProduct.html?itemID=" + product.getItemID() + "&"+ str + "'\" class=\"edit-button\">Edit Item</button><br/>\r\n" + //
          "                        <button onclick=\"location.href='editBatch.html?itemID=P" + product.getItemID() + "'\" class=\"edit-button\">Edit Batch</button><br/>\r\n" + //
          "                        <button onclick=\"location.href='info.html?itemID=P" + product.getItemID() +"'\" class=\"edit-button\">Info</button><br/>\r\n" + //
          "                    </div>               \r\n" + //
          "                </div>";
          out.println(resultBoxHtml);
        }

        ArrayList<Ingredient> ingredients = DatabaseGetter.getIngredients();
        // Add results box n times into the page for ingredients
        for(Ingredient ingredient : ingredients)
        {
        ArrayList<Batch> batches = DatabaseGetter.getIngredientBatches(ingredient.getItemID());
        String resultBoxHtml = "<div class=\"result-box\">\r\n" + //
        "                    <div class=\"product-ID\"> #I" + ingredient.getItemID() + "</div>\r\n" + //
        "                    <div class=\"image\"><img src=\"Images/" + DatabaseGetter.getImageFromID(ingredient.getItemID()) + "\" alt=\"No Image\"></div>\r\n" + //
        "                    <div class=\"product-name\">" + ingredient.getName() + "</div>\r\n" + //
        "                    <div class=\"batch-quantity\"> Number of batches: " + batches.size() + "  </div>\r\n" + //
        "                    <div class=\"buttons\">\r\n" + //
        "                        <button onclick=\"location.href='editIngredient.html?itemID=" + + ingredient.getItemID() + "'\" class=\"edit-button\">Edit Item</button><br/>\r\n" + //
        "                        <button onclick=\"location.href='editBatch.html?itemID=I" + ingredient.getItemID() + "'\" class=\"edit-button\">Edit Batch</button><br/>\r\n" + //
        "                        <button onclick=\"location.href='info.html?itemID=I" + ingredient.getItemID() +"'\" class=\"edit-button\">Info</button><br/>\r\n" + //
        "                    </div>               \r\n" + //
        "                </div>";
          out.println(resultBoxHtml);
        }


      }
      // Copy html if it is not part of results box
      else
      {
        out.println(nextLine);
      }
    }
    scan.close();
    // DatabaseSetter.addProduct(new Product(0, "Waffles", "WAFFLES ARE YUMMY"));
  }
}

