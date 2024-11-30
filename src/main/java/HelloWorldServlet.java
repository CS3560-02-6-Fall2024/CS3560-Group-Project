import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class HelloWorldServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html;");
    response.getWriter().println("<h1>TEST</h1>");

    String name = request.getParameter("name");

    PrintWriter out = response.getWriter();
    out.println("<h1>Hello " + name + "</h1>");
    out.println("<p>Nice to meet you!</p>");


    System.out.println("Test");
  }
}

