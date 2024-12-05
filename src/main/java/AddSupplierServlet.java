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

@WebServlet("/addSupplier.html")
public class AddSupplierServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
         // Get parameters
        String name = request.getParameter("supplier-name");
        String phone = request.getParameter("supplier-phone");
        String email = request.getParameter("supplier-email");
        String address = request.getParameter("supplier-address");

        // Set up the response to be HTML
        response.setContentType("text/html;");
        PrintWriter out = response.getWriter();

        // Path to the HTML file
        File html = new File(System.getProperty("user.dir") + "/src/main/webapp/addSupplier.html");
        Scanner scan = new Scanner(html, "UTF-8");

        String message = verifyInput(name, phone, email, address);
        // Read the HTML file line by line and output to the response
        while (scan.hasNextLine()) {
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
            else {
                // Output the other lines as-is
                out.println(nextLine);
            }
        }

        // Close the scanner
        scan.close();
    }

    // Verifies the input from webapp and returns a message to display
  private String verifyInput(String name, String phone, String email, String address)
  {
    if(name == null)
    {
      return "";
    }
    if(name.equals(""))
    {
      return "Missing Name";
    }
    if(phone == null || phone.equals(""))
    {
      return "Missing phone.";
    }
    if(email == null || email.equals(""))
    {
      return "Missing email.";
    }
    if(address == null || address.equals(""))
    {
      return "Missing address.";
    }
    if(!DatabaseGetter.checkForDuplicateProducts(name))// CHANGE THIS
    {
      return "Duplicate supplier found.";
    }
    // Add Supplier
    Supplier supplier = new Supplier(name, phone, email, address);
    DatabaseSetter.insertSupplier(supplier);
    // Insert supplier here
    return "Supplier added successfully";
  }
}


