package sample.Strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe BDConnector
 * Classe de connexion à la base de données.
 */
public class MySQL_Connector implements ConnectorInterface {
    public static Connection cnx;
    private static MySQL_Connector uniqueInstance;

    /**
     * Mettre le constructeur en privé
     */
    private MySQL_Connector() {
    }

    /**
      Permet d'obtenir l'instance de MySQL_Connector
     * @return MySQL_Connector
     */

    public static synchronized ConnectorInterface getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new MySQL_Connector();
        }
        return uniqueInstance;
    }


    /**
     * Méthode connect
     * Permet de se connecter à la base de données.
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
