import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SupplierServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        //get parameters
        String itemID = request.getParameter("itemID");
        boolean product = itemID.substring(0,1).equals("P");
        int id = Integer.parseInt(itemID.substring(1));


    }




}
