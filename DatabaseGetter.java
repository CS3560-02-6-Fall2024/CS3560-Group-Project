
import java.sql.*;

public class DatabaseGetter 
{
    //TODO: Find out what to put here
    static final String DB_URL = "";
    static final String USER = "";
    static final String PASSWORD = "";

    public static void main(String[] args)
    {
        try
        {
            //get connector
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
           

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

}