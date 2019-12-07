package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BDConnector {
    public static Connection cnx;
    public static Statement st;

    /**
     * methode de connexion a la base de donnee
     * */
    public static void connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/cinema?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";
        cnx = DriverManager.getConnection(url, user, password);
        st = cnx.createStatement();
    }
}
