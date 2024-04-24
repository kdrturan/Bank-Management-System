
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tncya
 */
public class BankDBConnection {
    public static Connection connectBankDB()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:bankdata.db");
            System.out.println("Connected");
            return con;
        }
        catch(Exception e)
        {
           System.out.println("Failed");
           return null;
        }
    }
}
