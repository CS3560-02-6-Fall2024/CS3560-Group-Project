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
    System.out.println(message);
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/addProduct.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
      out.println(nextLine);
    }
    scan.close();
    
  }
  
  // Verifies the input from webapp and returns a message to display
  private String verifyInput(String name, String description, String price, String photoFile, String[] quantities, String[] ingredients)
  {
    if(ingredients == null || ingredients.length == 0)
    {
      return "Enter at least one ingredient";
    }
    for(String q : quantities)
    {
      if(q == null)
      {
        return "Ingredient missing quantity value.";
      }
    }
    for(String i : ingredients)
    {
      if(i == null)
      {
        return "Missing ingredient value";
      }
    }
    if(name == null || name.equals(""))
    {
      return "Missing name";
    }
    if(description == null || description.equals(""))
    {
      return "Missing description.";
    }
    if(price == null || price.equals(""))
    {
      return "Missing price.";
    }
    if(!DatabaseGetter.checkForDuplicateProducts(name))
    {
      return "Duplicate product found.";
    }
    
    // Check for photo file (optional file)

    // Add ingredients into recipe table

    // Add Product
    DatabaseSetter.addProduct(new Product(name, description, Float.parseFloat(price)));

    return "Product added successfully";
  }
}

