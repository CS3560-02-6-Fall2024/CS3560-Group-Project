import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editProduct.html")
public class EditProductServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    
    // Get parameters
    String name = request.getParameter("productName");
    String description = request.getParameter("productDescription");
    String price = request.getParameter("price");
    String photoFile = request.getParameter("photoFile");
    String[] quantities = request.getParameterValues("quantity");
    String[] units = request.getParameterValues("unit");
    String[] ingredients = request.getParameterValues("ingredient");
    // System.out.println("Add Next Product:" +
    //                     "\n Product Name: " + name +
    //                     "\n Product Description: " + description +
    //                     "\n Price: " + price +
    //                     "\n Photofile: " + photoFile +
    //                     "\n quantitys: " + quantities +
    //                     "\n ingredients: " + ingredients);

    // String [] debug = request.getParameterValues("ingredientList");  
    // for (String string : debug) {
    //   System.out.println("ingredient: " + string);
    // }                  
    Product prod;

    String pname;
    String pdescription;
    float pprice;
    // Update in database
    if(request.getParameter("save") != null)
    {
      System.out.println("Save");
      prod = new Product(Integer.parseInt(request.getParameter("itemID")), name, description, Float.parseFloat(price));
      pname = prod.getName();
      pdescription = prod.getDescription();
      pprice = prod.getPrice();
    }
    else if(request.getParameter("delete") != null)
    {
      prod = DatabaseGetter.getProductFromID(Integer.parseInt(request.getParameter("itemID")));
      DatabaseSetter.deleteProduct(prod);
      // Reset values
      name=null;
      description=null;
      price=null;
      photoFile=null;
      quantities=null;
      units=null;
      ingredients=null;
      pname = null;
      pdescription = null;
      pprice = 0;
    }
    // Populate fields with current data
    else
    {
      prod = DatabaseGetter.getProductFromID(Integer.parseInt(request.getParameter("itemID")));
      pname = prod.getName();
      pdescription = prod.getDescription();
      pprice = prod.getPrice();
    }
    
    // Try to add product to database
    String message = verifyInput(name, description, price, photoFile, quantities, ingredients, units, request.getParameter("save") != null, request.getParameter("delete") != null, prod);
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/editProduct.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
      // Add message html
      if(nextLine.contains("div class=\"message\""))
      {
        String color = "red";
        if(message.contains("successfully"))
        {
          color="green";
        }
        out.println("<div class=\"message\" style=\"color:" + color + "\">" + message + "</div>");
      }
      else if(nextLine.contains("form class=\"form\""))
      {
        out.println(nextLine);
        ArrayList<Ingredient> ingredients2 = DatabaseGetter.getIngredients();
        out.println("<input type=\"hidden\" name=\"itemID\" value=\"" + prod.getItemID() + "\">");
        for (int i = 0; i < ingredients2.size(); i++) 
        {
          out.println("<input type=\"hidden\" name=\"ingredientList\" value=\"" + ingredients2.get(i).getName() + "\">");
        }
        out.println("<div class=\"top\">\r\n" + //
                    "                    <div class=\"left-side\">\r\n" + //
                    "                        <div class=\"product-name\">\r\n" + //
                    "                            <label>* Product Name</label>\r\n" + //
                    "                            <input type=\"text\" placeholder=\"Enter here\" value=\"" + pname + "\" name=\"productName\">\r\n" + //
                    "                        </div>\r\n" + //
                    "                        <div class=\"price\">\r\n" + //
                    "                            <label for=\"price\">Price</label>\r\n" + //
                    "                            <input type=\"number\" step=\"0.01\" name=\"price\"  value=\"" + pprice + "\" >\r\n" + //
                    "                        </div>\r\n" + //
                    "                    </div>\r\n" + //
                    "                    <div class=\"right-side\">\r\n" + //
                    "                        <div class=\"description\">\r\n" + //
                    "                            <label> Description</label>\r\n" + //
                    "                            <input type=\"text\" style=\"padding-bottom: 7em; padding-right: 7%\" placeholder=\"Enter here\" name=\"productDescription\"  value=\"" + pdescription + "\" >\r\n" + //
                    "                        </div>\r\n" + //
                    "                    </div>\r\n" + //
                    "                </div>\r\n" + //
                    "                <div class=\"bottom\">\r\n" + //
                    "                    <div class=\"left-side\">\r\n" + //
                    "                        <label for=\"add-photo\" class=\"add-photo-label\">Update Photo</label>\r\n" + //
                    "                        <label for=\"file-upload\" class=\"add-photo\">+</label>\r\n" + //
                    "                        <input type = \"file\" id=\"file-upload\" class=\"add-photo\" name=\"photoFile\">\r\n" + //
                    "                    </div>\r\n" + //
                    "                    <div class=\"right-side\">\r\n" + //
                    "                        <div class=\"ingredients\" id=\"ingredients\">\r\n" + //
                    "                        </div>\r\n" + //
                    "                    </div>\r\n" + //
                    "                </div>");
        while(!scan.nextLine().contains("</form>"))
        {
          // Consume lines until we reach the end of the form
        }
        out.println("</form>");
      }
      else
      {
        out.println(nextLine);
      }
    }
    scan.close();
    
  }
  
 // Verifies the input from webapp and returns a message to display
 private String verifyInput(String name, String description, String price, String photoFile, String[] quantities, String[] ingredients, String[] units, boolean update, boolean delete, Product prod)
 {
   if(name == null && !delete)
   {
    return "";
   }
   if(delete)
    return "Product removed successfully";
   if(name.equals(""))
   {
     return "Missing Name";
   }
   if(description == null || description.equals(""))
   {
     return "Missing description.";
   }
   if(price == null || price.equals("0.00"))
   {
     return "Missing price.";
   }
   if(ingredients == null || ingredients.length == 0)
   {
     return "Enter at least one ingredient";
   }
   for(String q : quantities)
   {
     if(q == null || q.equals(""))
     {
       return "Ingredient missing quantity value.";
     }
   }
   for(String i : ingredients)
   {
     if(i == null || i.equals(""))
     {
       return "Missing ingredient value";
     }
   }
   for(String u : units)
   {
     if(u == null || u.equals(""))
     {
       return "Missing units value";
     }
   }

  if(update)
  {
    DatabaseSetter.updateProduct(prod);

    if(photoFile != null && !photoFile.equals(""))
      DatabaseSetter.updateImage(new Image(prod.getItemID(), photoFile));
    
    if(ingredients != null && ingredients.length != 0 && !ingredients[0].equals(""))
    {
      ArrayList<RecipeIngredient> ing = new ArrayList<RecipeIngredient>();
      int id = DatabaseGetter.getLastRecipeIngredientID() + 1;
      // Add ingredients into recipe table
      for(int i = 0; i < ingredients.length; i++)
      {
        RecipeIngredient r = new RecipeIngredient(id + i, prod.getItemID(), DatabaseGetter.getItemIDFromName(ingredients[i]), Float.parseFloat(quantities[i]), units[i]);
        ing.add(r);
      }
      DatabaseSetter.updateRecipe(prod, ing);
    }
    
    return "Product updated successfully";
    

  }
  return "Something went wrong";
 
}
}

