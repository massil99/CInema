package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BDConnector {
    public static Connection cnx;
    public static Statement st;

    /**
     * Méthode connect
     * Permet de se connecter à la base de données.
     * */
    public static void connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/cinema?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        cnx = DriverManager.getConnection(url, user, password);
        st = cnx.createStatement();
    }
}
