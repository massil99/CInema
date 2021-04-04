package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe BDConnector
 * Classe de connexion � la base de donn�es.
 */
public class BDConnector {
    public static Connection cnx;

    /**
     * M�thode connect
     * Permet de se connecter � la base de donn�es.
     * */
    public static Connection connect() throws SQLException, ClassNotFoundException{
        if(cnx != null)
            return cnx;

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/cinema?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        cnx = DriverManager.getConnection(url, user, password);
        return cnx;
    }
}
