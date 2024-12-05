import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addIngredient.html")
public class AddIngredientServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    // Get parameters
    String name = request.getParameter("ingredientName");
    String description = request.getParameter("description");
    String storageInstructions = request.getParameter("storageInstructions");
    String photoFile = request.getParameter("photoFile");
    System.out.println("Add Next Product:" +
                        "\n Ingredient Name: " + name +
                        "\n Ingredient Description: " + description +
                        "\n Photofile: " + photoFile);



    // Try to add product to database
    String message = verifyInput(name, storageInstructions, description, photoFile);
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/addIngredient.html");
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
      else
      {
        out.println(nextLine);
      }
    }
    scan.close();
    
  }
  
  // Verifies the input from webapp and returns a message to display
  private String verifyInput(String name, String storageInstructions, String description, String photoFile)
  {
    if(name == null)
    {
      return "";
    }
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
    if(!DatabaseGetter.checkForDuplicateProducts(name))
    {
      return "Duplicate ingredient found.";
    }
    
    // Add Product
    Ingredient ingredient = new Ingredient(name, storageInstructions, description);
    DatabaseSetter.insertIngredient(ingredient);

    // Check for photo file (optional file)
    if(photoFile != null && !photoFile.equals(""))
    {
      DatabaseSetter.insertImage(new Image(ingredient.getItemID(), photoFile));
    }
    return "Ingredient added successfully";
  }
}

