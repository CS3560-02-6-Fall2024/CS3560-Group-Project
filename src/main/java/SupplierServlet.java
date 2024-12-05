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
@WebServlet("/supplier.html")
public class SupplierServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("Supplier page");
        // Render html page (just copies the html of the page that you are using)
        File html = new File(System.getProperty("user.dir") + "/src/main/webapp/supplier.html");
        Scanner scan = new Scanner(html, "UTF-8");
        response.setContentType("text/html;");
        PrintWriter out = response.getWriter();
        while(scan.hasNextLine())
        {
        String nextLine = scan.nextLine();
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
            
            ArrayList<Supplier> suppilers = DatabaseGetter.getSuppliers();
            for (Supplier supplier : suppilers) 
            {
            System.out.println("Result box html start");
            String resultBoxHtml = "<div class=\"result-box\">\r\n" + //
                                "                    <div class=\"supplier-info\">\r\n" + //
                                "                        <div><strong>Name:</strong> " + supplier.getName() + "</div>\r\n" + //
                                "                        <div><strong>Phone:</strong> " + supplier.getPhoneNumber() + "</div>\r\n" + //
                                "                        <div><strong>Email:</strong> " + supplier.getEmail() + "</div>\r\n" + //
                                "                        <div><strong>Address:</strong> " + supplier.getAddress() + "</div>\r\n" + //
                                "                    </div>\r\n" + //
                                "                    <div class=\"buttons\">\r\n" + //
                                "                        <button onclick=\"location.href='editSupplier.html?supplierID="+supplier.getID()+"'\" class=\"edit-button\">Edit</button><br/>\r\n" + //
                                "                    </div>\r\n" + //
                                "                </div>";
            out.println(resultBoxHtml);
            }
        }
        out.println(nextLine);
        }
        scan.close();

        
    }




}

/*
 * In hmtl there is result thing
 *Needs to change w/ data in database
 * 
 * "div class" - ctrl shift i
 * "divider"
 * insert text name as string into the value
 * (results box specifically)
 * also shows style sheet
 * 
 * Populate result box w/ info 
 * html copy cant work
 * 
 * 1st part get relative path (everyone's computer will be different
 * 2nd part scanner then goes to read html
 * 3rd line : response httpservlet response (passed back to front end)
 *  used to display after api calls
 * "text.html" specificall
 * 5th is writing
 * overall: allow us to rewrite html?
 *  servlets are a webpage maker
 *  servlet would overwrite, so we rewrite html
 */