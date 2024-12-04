import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/info.html")
public class InfoServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
	// Get parameters
	
	String itemID = request.getParameter("itemID");
	boolean product = itemID.substring(0,1).equals("P");
	int id = Integer.parseInt(itemID.substring(1));

	// Parameters we are going to use to populate page

	String name = "";
	String description = "";
	int numBatches = 0;

	// If the item is a product
	if(product)
	{
		Product prod = DatabaseGetter.getProductFromID(id);
		name = prod.getName();
		description = prod.getDescription();
	}
	// else if the item is an ingredient
	else
	{
		Ingredient ingredient = DatabaseGetter.getIngredientFromID(id);
		name = ingredient.getName();
		description = ingredient.getDescription();
	}
	


    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/info.html");
    Scanner scan = new Scanner(html, "UTF-8");
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
	while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();
	  // Dynamically populate description
	  if(nextLine.contains("div class=\"descripText\""))
	  {
		out.println("<div class=\"descripText\">" + description + "</div>");
	  }
	  // Dynamically populate itemID
	  else if(nextLine.contains("id=\"itemID\""))
	  {
		out.println("<div class=\"head\" id=\"itemID\">#" + itemID + "</div>");
	  }
	  // Dynamically populate itemName
	  else if(nextLine.contains("id=\"itemName\""))
	  {
		out.println("<div class=\"head\" id=\"itemName\">" + name + "</div>");
	  }
	  // Dynamically populate numBatches
	  else if(nextLine.contains("id=\"batchNum\""))
	  {
		out.println("<div class=\"batchNum\" id=\"batchNum\"> Number of Batches: " + numBatches + " </div>");
	  }
	  else if(nextLine.contains("Start Ingredients Box"))
	  {
		// Skip ingredients list if we are showing an ingredient
		if(!product)
		{
			while(!scan.nextLine().contains("End Ingredients Box"))
			{
				//We are "eating" lines in the condition of the while loop, searching for the end of the div
			}
		}

	  }
	  // Copy all static html
	  else
	  {
		out.println(nextLine);
	  }
	  
    }
	System.out.println(DatabaseGetter.getProductFromID(id).getName());
    scan.close();
  }
}

