import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
    String[] ingredients = request.getParameterValues("ingredient");
    System.out.println("Add Next Product:" +
                        "\n Product Name: " + name +
                        "\n Product Description: " + description +
                        "\n Price: " + price +
                        "\n Photofile: " + photoFile +
                        "\n quantitys: " + quantities +
                        "\n ingredients: " + ingredients);



    // Try to add product to database
    String message = verifyInput(name, description, price, photoFile, quantities, ingredients);
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
      else
      {
        out.println(nextLine);
      }
    }
    scan.close();
    
  }
  
  // Verifies the input from webapp and returns a message to display
  private String verifyInput(String name, String description, String price, String photoFile, String[] quantities, String[] ingredients)
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
    
    // Add Product
    Product prod = new Product(name, description, Float.parseFloat(price));
    DatabaseSetter.insertProduct(prod);

    // Check for photo file (optional file)
    if(photoFile != null && !photoFile.equals(""))
    {
      DatabaseSetter.insertImage(new Image(prod.getProductID(), photoFile));
    }


    // Add ingredients into recipe table



    return "Product added successfully";
  }
}

