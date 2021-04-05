package sample;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle_Connector implements ConnectorInterface {
    public static Connection cnx;
    private static Oracle_Connector uniqueInstance;

    /**
     * Metter le constructeur en privé
     */
    private Oracle_Connector() {
    }

    /**
     Permet d'obtenir l'instance d'Oracle_Connector
     * @return Oracle_Connector
     */

    public static synchronized ConnectorInterface getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Oracle_Connector();
        }
        return uniqueInstance;
    }


    /**
     * Méthode connect
     * Permet de connecter avec la base de données Oracle
     * */
    public static Connection connect() throws SQLException, ClassNotFoundException{
        if(cnx != null)
            return cnx;

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "root";
        String password = "root";
        cnx = DriverManager.getConnection(url, user, password);
        System.out.println("Successfully connected");
        return cnx;

    }

}
