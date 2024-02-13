import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    static Connection conn = null;
    static Connection instance()
    {
        if(conn != null)
            return conn;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/test/db",
                "sa",
                "pwd2"        
            );
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
