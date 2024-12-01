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

    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/addProduct.html");
    Scanner scan = new Scanner(html);
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
      System.out.println(nextLine);
      out.println(nextLine);
    }
    System.out.println(scan.nextLine());
    scan.close();
    // DatabaseGetter.testDatabaseGetter();
    DatabaseSetter.addProduct(new Product(0, "Waffles", "WAFFLES ARE YUMMY"));
  }
}

