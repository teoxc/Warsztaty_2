import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection()throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/?useSSL=false",
                        "root", "CodersLab");
    }
}
