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
                
                ArrayList<SupplierBatch> supplierBatchArray = DatabaseGetter.getSupplierBatch();
                //so i need a getter for the product
                
                // Add results box n times into the page
                for(SupplierBatch supplierBatch : supplierBatchArray)
                {

                    
                    // until ben gets up the company batch thingy i will put mock data
                    //TODO: updateh when it done
                    String price = "$123.20"; // use the getter for supplierBatch when implemented into database
                    int ingredientID = supplierBatch.getIngredientId();
                    Ingredient ingredient = DatabaseGetter.getIngredientFromId(ingredientID);
                    String ingredientName = ingredient.getName(); // use product.getName()

                    
                    String resultBoxHtml = "<div class=\"result-box\">\r\n" + //
                    "                    <div class=\"product-ID\"> #P" + price + "</div>\r\n" + //
                    "                    <div class=\"image\"><img src=\"Images/" + DatabaseGetter.getImageFromID(ingredientID) + "\" alt=\"No Image\"></div>\r\n" + //
                    "                    <div class=\"product-name\">" + ingredientName + "</div>\r\n" + //
                    "                    <div class=\"buttons\">\r\n" + //
                    "                        <button onclick=\"location.href='order.html'\" class=\"edit-button\">Order</button>" + //
                    "                        <button onclick=\"location.href='supplierInfo.html'\" class=\"edit-button\">Supplier</button>" + //
                    "                    </div>               \r\n" + //
                    "                </div>";
                      out.println(resultBoxHtml);


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