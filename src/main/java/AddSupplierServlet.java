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
        // Set up the response to be HTML
        response.setContentType("text/html;");
        PrintWriter out = response.getWriter();

        // Path to the HTML file
        File html = new File(System.getProperty("user.dir") + "/src/main/webapp/addSupplier.html");
        Scanner scan = new Scanner(html, "UTF-8");

        // Fetch the list of ingredients from the database (or other data source)
        ArrayList<Ingredient> ingredients = DatabaseGetter.getIngredients();

        // Build the query string with all the ingredients
        StringBuilder ingredientListBuilder = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientListBuilder.append("ingredientList=" + ingredients.get(i).getName());
            if (i != ingredients.size() - 1) {
                ingredientListBuilder.append("&");
            }
        }

        // Read the HTML file line by line and output to the response
        while (scan.hasNextLine()) {
            String nextLine = scan.nextLine();

            // Look for the line containing "Add Supplier" and modify it dynamically
            if (nextLine.contains("Add Supplier")) {
                // Create the button with the dynamic ingredient list query parameters
                out.println("<button onclick=\"location.href='addSupplierForm.html?" + ingredientListBuilder + "'\" class=\"button\">Add Supplier<img src='Images\\Supplier.png'></button>");
            } else {
                // Output the other lines as-is
                out.println(nextLine);
            }
        }

        // Close the scanner
        scan.close();
    }
}


