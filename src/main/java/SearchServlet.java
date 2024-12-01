import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search.html")
public class SearchServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {

    // Render html page (just copies the html of the page that you are using)
    File html = new File(System.getProperty("user.dir") + "/src/main/webapp/search.html");
    Scanner scan = new Scanner(html);
    response.setContentType("text/html;");
    PrintWriter out = response.getWriter();
    while(scan.hasNextLine())
    {
      String nextLine = scan.nextLine();

      // Copy the result box html so we can to populate page
      if(nextLine.contains("START RESULT BOX"))
      {
        String resultBoxHtml = "";
        while(scan.hasNextLine())
        {
          nextLine = scan.nextLine();
          // Break out of result box loop if we reach the end of the result box
          if(nextLine.contains("END RESULT BOX"))
            break;
          resultBoxHtml += nextLine + "\n";
        }
        // Add results box n times into the page
        for(int i = 0; i < 3; i++)
        {
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
    DatabaseGetter.testDatabaseGetter();
  }
}

