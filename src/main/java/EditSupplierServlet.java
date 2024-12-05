import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editSupplier.html")
public class EditSupplierServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
         // Get parameters
        String name = request.getParameter("supplier-name");
        String phone = request.getParameter("supplier-phone");
        String email = request.getParameter("supplier-email");
        String address = request.getParameter("supplier-address");


        Supplier supplier;

        String pname;
        String pphone;
        String pemail;
        String paddress;

        // Update in database
        if(request.getParameter("save") != null)
        {
          supplier = new Supplier(Integer.parseInt(request.getParameter("supplierID")), name, phone, email, address);
          pname = name;
          pphone = phone;
          pemail = email;
          paddress = address;
        }
        else if(request.getParameter("delete") != null)
        {
          supplier = DatabaseGetter.getSupplierFromID(Integer.parseInt(request.getParameter("supplierID")));
          DatabaseSetter.deleteSupplier(supplier.getID());
          name = null;
          phone = null;
          email = null;
          address = null;
          pname = "";
          pphone = "";
          pemail = "";
          paddress = "";
        }
        // Populate fields with current data
        else
        {
          supplier = DatabaseGetter.getSupplierFromID(Integer.parseInt(request.getParameter("supplierID")));
          pname = supplier.getName();
          pphone = supplier.getPhoneNumber();
          pemail = supplier.getEmail();
          paddress = supplier.getAddress();
        }

        // Set up the response to be HTML
        response.setContentType("text/html;");
        PrintWriter out = response.getWriter();

        // Path to the HTML file
        File html = new File(System.getProperty("user.dir") + "/src/main/webapp/editSupplier.html");
        Scanner scan = new Scanner(html, "UTF-8");

        String message = verifyInput(name, phone, email, address, request.getParameter("save") != null, request.getParameter("delete") != null, supplier);
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
            else if(nextLine.contains("<form>"))
            {
              // Skip past this result box section
              while(scan.hasNextLine())
              {
              nextLine = scan.nextLine();
              // Break out of result box loop if we reach the end of the result box
              if(nextLine.contains("</form>"))
                  break;
              }
              String form = "<form>\r\n <input type=\"hidden\" name=\"supplierID\" value=\"" + supplier.getID() + "\">\r\n" + //
                  "                <label for=\"supplier-name\">Supplier Name:</label><br>\r\n" + //
                  "                <input type=\"text\" id=\"supplier-name\" name=\"supplier-name\" value=\"" + pname + "\" placeholder=\"Enter name...\"><br>\r\n" + //
                  "            \r\n" + //
                  "                <label for=\"supplier-phone\">Phone:</label><br>\r\n" + //
                  "                <input type=\"text\" id=\"supplier-phone\" name=\"supplier-phone\" value=\"" + pphone + "\" placeholder=\"Enter phone...\"><br>\r\n" + //
                  "            \r\n" + //
                  "                <label for=\"supplier-email\">Email:</label><br>\r\n" + //
                  "                <input type=\"text\" id=\"supplier-email\" name=\"supplier-email\" value=\"" + pemail + "\" placeholder=\"Enter email...\"><br>\r\n" + //
                  "            \r\n" + //
                  "                <label for=\"supplier-address\">Address:</label><br>\r\n" + //
                  "                <input type=\"text\" id=\"supplier-address\" name=\"supplier-address\" value=\"" + paddress + "\" placeholder=\"Enter address...\"><br>\r\n" + //
                  "            \r\n" + //
                  "                <button type=\"submit\" name=\"save\" value=\"true\">Submit</button>\r\n" + //
                  "                <button type=\"submit\" id=\"remove-button\" name=\"delete\" value=\"true\">Remove</button>\r\n" + //
                  "            </form>";
              out.println(form);
            }
            else 
            {
                // Output the other lines as-is
                out.println(nextLine);
            }
        }

        // Close the scanner
        scan.close();
    }

    // Verifies the input from webapp and returns a message to display
  private String verifyInput(String name, String phone, String email, String address, boolean save, boolean delete, Supplier supplier)
  {
    if(name == null && !delete)
    {
      return "";
    }
    if(delete)
      return "Supplier removed successfully";
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
    if(save)
    {
      // Update supplier
      DatabaseSetter.updateSupplier(supplier);

      return "Supplier updated successfully";
    }
    return "Something went wrong";
  }
}


