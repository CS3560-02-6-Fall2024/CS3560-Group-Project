import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editIngredient.html")
public class EditIngredientServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    
      // Get parameters
      String name = request.getParameter("ingredientName");
      String description = request.getParameter("ingredientDescription");
      String storageInstructions = request.getParameter("storageInstructions");
      String photoFile = request.getParameter("photoFile");
      System.out.println("Add Next Product:" +
                          "\n Ingredient Name: " + name +
                          "\n Ingredient Description: " + description +
                          "\n Photofile: " + photoFile);
   
    Ingredient ing;
    String pname;
    String pdescription;
    String pstorageInstructions;
    // Update in database
    if(request.getParameter("save") != null)
    {
      ing = new Ingredient(Integer.parseInt(request.getParameter("itemID")), name, storageInstructions, description);
      pname = ing.getName();
      pdescription = ing.getDescription();
      pstorageInstructions = ing.getStorageIntructions();
    }
    else if(request.getParameter("delete") != null)
    {
      ing = DatabaseGetter.getIngredientFromID(Integer.parseInt(request.getParameter("itemID")));
      DatabaseSetter.deleteIngredient(ing);
      // Reset values
      name=null;
      description=null;
      photoFile=null;
      pname = "";
      pdescription = "";
      pstorageInstructions = "";
    }
    // Populate fields with current data
    else
    {
      ing = DatabaseGetter.getIngredientFromID(Integer.parseInt(request.getParameter("itemID")));
      pname = ing.getName();
      pdescription = ing.getDescription();
      pstorageInstructions = ing.getStorageIntructions();
    }
    System.out.println("DEBUG: pdescription " + pdescription + " pinst " + pstorageInstructions);
    // Try to add product to database
    String message = verifyInput(name, storageInstructions, description,  photoFile, request.getParameter("save") != null, request.getParameter("delete") != null, ing);
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/editIngredient.html");
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
        out.println("<input type=\"hidden\" name=\"itemID\" value=\"" + ing.getItemID() + "\">");
        for (int i = 0; i < ingredients2.size(); i++) 
        {
          out.println("<input type=\"hidden\" name=\"ingredientList\" value=\"" + ingredients2.get(i).getName() + "\">");
        }
        out.println("<div class=\"left-side\">\r\n" + //
                    "                    <div class=\"fieldLabel\">\r\n" + //
                    "                        <label>* Ingredient Name</label>\r\n" + //
                    "                        <input type=\"text\" id=\"product-name\" placeholder=\"Enter here\"  value=\"" + pname + "\" name=\"ingredientName\">\r\n" + //
                    "                    </div>\r\n" + //
                    "                    <div class=\"fieldLabel\">\r\n" + //
                    "                        <label for=\"price\">Storage Instructions</label>\r\n" + //
                    "                        <input type=\"text\" value=\"" + pstorageInstructions + "\" name=\"storageInstructions\">\r\n" + //
                    "                    </div>\r\n" + //
                    "                    \r\n" + //
                    "                    <div class=\"fieldLabel\">\r\n" + //
                    "                        <label>Description</label>\r\n" + //
                    "                        <input type=\"text\" style=\"padding-bottom: 14em;\" placeholder=\"Enter here\" value=\"" + pdescription + "\" name=\"ingredientDescription\">\r\n" + //
                    "                    </div>\r\n" + //
                    "                </div>\r\n" + //
                    "                <div class=\"right-side\">\r\n" + //
                    "                    <!-- <button class=\"add-photo\">+</button> -->\r\n" + //
                    "                    <label for=\"file-upload\" class=\"add-photo\">+</label>\r\n" + //
                    "                    <input type = \"file\" id=\"file-upload\" class=\"add-photo\" name=\"photoFile\">\r\n" + //
                    "                    <label for=\"add-photo\" class=\"add-photo-label\">Add Photo</label>\r\n" + //
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
  // Verifies the input from webapp and returns a message to display
  private String verifyInput(String name, String storageInstructions, String description, String photoFile, boolean save, boolean delete, Ingredient ingredient)
  {
    if(name == null)
    {
      return "";
    }
    if(delete)
      return "Ingredient removed successfully";
    if(name.equals(""))
    {
      return "Missing Name";
    }
    if(storageInstructions == null || storageInstructions.equals(""))
    {
      return "Missing storage instructions.";
    }
    if(description == null || description.equals(""))
    {
      return "Missing description.";
    }
    
    if(save)
    {
      // Add Product
      // Ingredient ingredient = new Ingredient(name, storageInstructions, description);
      DatabaseSetter.updateIngredient(ingredient);
      // Check for photo file (optional file)
      if(photoFile != null && !photoFile.equals(""))
      {
        DatabaseSetter.updateImage(new Image(ingredient.getItemID(), photoFile));
      }
      return "Ingredient updated successfully";
    }
    return "Something went wrong";
  }
}

