import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//when suppliers.html is accessed
@WebServlet("/suppliers.html")
public class SupplierServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        // Render html page (just copies the html of the page that you are using)
        File html = new File(System.getProperty("user.dir") + "/src/main/webapp/suppliers.html");
        Scanner scan = new Scanner(html, "UTF-8");
        response.setContentType("text/html;");
        PrintWriter out = response.getWriter();

        while(scan.hasNextLine())
        {
            String nextLine = scan.nextLine();

            // Copy the result box html so we can to populate page
            if(nextLine.contains("START RESULT BOX"))
            {
                // Skip past this result box section
                while(scan.hasNextLine())
                {
                    nextLine = scan.nextLine();
                    // Break out of result box loop if we reach the end of the result box
                    if(nextLine.contains("END RESULT BOX"))
                        break;
                }
                
                ArrayList<Supplier> suppliers = DatabaseGetter.getSuppliers();
                // Add results box n times into the page
                for(Supplier supplier : suppliers)
                {
                    out.println(supplier); //I'm not sure how to set up html boxes so for now it'll be this
                }
            }
            // Copy html if it is not part of results box
            else
            {
                out.println(nextLine);
            }
        }

        scan.close();

        
    }




}
