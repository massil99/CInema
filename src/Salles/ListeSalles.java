package Salles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe permettant d'interagire avec la table Salles de la base de donnee
 */
public class ListeSalles {
    private static Connection cnx;
    private static Statement st;
    ArrayList<Salle> salles;

    /**
     *  Methode permettant la connection a la base de donnee
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/cinema?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        cnx = DriverManager.getConnection(url, user, password);
        st = cnx.createStatement();
    }

    public ListeSalles() {
        try {
            connect();
            ResultSet res = st.executeQuery("SELECT * FROM salles");

            salles = new ArrayList<>();
            while(res.next()){
                salles.add(new Salle(res.getInt(1), res.getInt(2), res.getInt(3), res.getBoolean(4)));
            }
            res.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Ajout(Salle s) {
        try {
            connect();
            String query = "INSERT INTO Seance (capacité, nombreDepersonnes, est_dispo) VALUES"
                    + "('"+ s.getCapacite() +"',"
                    + "'" + s.getNombreDepersonnes() + "',"
                    + "'"+ s.isEstDispo() +"')";

            salles.add(s);
            st.executeUpdate(query);
        }catch( Exception e) {
            e.printStackTrace();
        }
    }

    public void Suppression(int id ) {
        try {
            connect();
            String query="DELETE FROM salle WHERE id_salle='"+ id +"'";

            salles.remove(getSalle(id));

            st.executeUpdate(query);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public Salle getSalle(int id) {
        for (Salle s : salles) {
            if (s.getNumeroSalle() == id)
                return s;
        }
        return null;
    }

    /** Getter **/
    public ArrayList<Salle> getSalles() {
        return salles;
    }
}

