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

@WebServlet("/add.html")
public class AddServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/add.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
      ArrayList<Ingredient> ingredients = DatabaseGetter.getIngredients();
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < ingredients.size(); i++) 
      {
        str.append("ingredientList=" + ingredients.get(i).getName());
        if(i != ingredients.size() - 1)
        {
          str.append("&");
        }
      }
      if(nextLine.contains("Add Product"))
      {
        out.println("<button onclick=\"location.href='addProduct.html?" + str + "'\" class=\"button\">Add Product<img src = \"Images\\Bread.png\"></button>");
      }
      else
      {
        out.println(nextLine);
      }
    }
    scan.close();
    
  }
}

