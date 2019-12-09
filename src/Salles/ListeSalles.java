package Salles;

import sample.BDConnector;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe permettant d'interagire avec la table Salles de la base de donnee
 */
public class ListeSalles {
    ArrayList<Salle> salles;

    public ListeSalles() {
        try {
            BDConnector.connect();
            ResultSet res = BDConnector.st.executeQuery("SELECT * FROM salles");

            salles = new ArrayList<>();
            while(res.next()){
                salles.add(new Salle(res.getInt("id_salle"),
                        res.getInt("capacite"),
                        res.getInt("nombreDePersonnes"),
                        (res.getInt("est_dispo") == 1)? true:false));
            }
            res.close();
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Ajout(Salle s) {
        try {
            BDConnector.connect();
            String query = "INSERT INTO salles (capacite, nombreDepersonnes, est_dispo) VALUES"
                    + "('"+ s.getCapacite() +"',"
                    + "'" + s.getNombreDepersonnes() + "',"
                    + "'"+ ((s.isEstDispo()) ? 1 : 0) +"')";
            salles.add(s);
            BDConnector.st.executeUpdate(query);
        }catch( Exception e) {
            e.printStackTrace();
        }
    }

    public void Suppression(int id ) {
        try {
            BDConnector.connect();
            String query="DELETE FROM salles WHERE id_salle='"+ id +"'";

            salles.remove(getSalle(id));

            BDConnector.st.executeUpdate(query);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** Getter **/

    public Salle getSalle(int id) {
        for (Salle s : salles) {
            if (s.getNumeroSalle() == id)
                return s;
        }
        return null;
    }

    public ArrayList<Salle> getSalles() {
        return salles;
    }
}

