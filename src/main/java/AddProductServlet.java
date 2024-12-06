import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProduct.html")
public class AddProductServlet extends HttpServlet {

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
    System.out.println("Add Next Product:" +
                        "\n Product Name: " + name +
                        "\n Product Description: " + description +
                        "\n Price: " + price +
                        "\n Photofile: " + photoFile +
                        "\n quantitys: " + quantities +
                        "\n ingredients: " + ingredients);

    // String [] debug = request.getParameterValues("ingredientList");  
    // for (String string : debug) {
    //   System.out.println("ingredient: " + string);
    // }                  
    // Try to add product to database
    String message = verifyInput(name, description, price, photoFile, quantities, ingredients, units);
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/addProduct.html");
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
      else if(nextLine.contains("form id=\"form\""))
      {
        out.println(nextLine);
        ArrayList<Ingredient> ingredients2 = DatabaseGetter.getIngredients();
        for (int i = 0; i < ingredients2.size(); i++) 
        {
          out.println("<input type=\"hidden\" name=\"ingredientList\" value=\"" + ingredients2.get(i).getName() + "\">");
        }
      }
      else
      {
        out.println(nextLine);
      }
    }
    scan.close();
    
  }
  
  // Verifies the input from webapp and returns a message to display
  private String verifyInput(String name, String description, String price, String photoFile, String[] quantities, String[] ingredients, String[] units)
  {
    if(name == null)
    {
      return "";
    }
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
    if(!DatabaseGetter.checkForDuplicateProducts(name))
    {
      return "Duplicate product found.";
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
    
    // Add Product
    Product prod = new Product(name, description, Float.parseFloat(price));
    DatabaseSetter.insertProduct(prod);

    // Check for photo file (optional file)
    if(photoFile != null && !photoFile.equals(""))
    {
      DatabaseSetter.insertImage(new Image(prod.getItemID(), photoFile));
    }


    // Add ingredients into recipe table
    for(int i = 0; i < ingredients.length; i++)
    {
      RecipeIngredient r = new RecipeIngredient(prod.getItemID(), DatabaseGetter.getItemIDFromName(ingredients[i]), Float.parseFloat(quantities[i]), units[i]);
      RecipeIngredient.CreateRecipeIngredient(r);
    }
    

    return "Product added successfully";
  }
}

