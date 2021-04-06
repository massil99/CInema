package Salles;

import sample.Strategy.ConnectorInterface;
import sample.Strategy.MySQL_Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe ListeSalles
 * Classe permettant d'interagir avec la table Salles de la base de donn�es.
 */
public class ListeSalles {
    ArrayList<Salle> salles;
    ConnectorInterface connector;

    /**
     * Constructeur ListeSalles
     * Chargement des salles en local.
     */
    public ListeSalles() {
        try {
            //Connection connection =  MySQL_Connector.connect();
            this.connector = MySQL_Connector.getInstance();
            Connection connection = ((MySQL_Connector)connector).connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM salles");

            salles = new ArrayList<>();
            while(res.next()){
                salles.add(new Salle(res.getInt("id_salle"),
                        res.getInt("capacite"),
                        res.getInt("nombreDePersonnes"),
                        (res.getInt("est_dispo") == 1)? true:false));
            }
            res.close();
           statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode Ajout
     * Ajoute une salle à la base de données.
     * @param s La salle à ajouter.
     */
    public void Ajout(Salle s) {
        try {
            Connection connection = ((MySQL_Connector)connector).connect();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO salles (capacite, nombreDepersonnes, est_dispo) VALUES"
                    + "('"+ s.getCapacite() +"',"
                    + "'" + s.getNombreDepersonnes() + "',"
                    + "'"+ ((s.isEstDispo()) ? 1 : 0) +"')";

            if(statement.executeUpdate(query) == 1)
                salles.add(s);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode Suppression
     * Supprime une salle de la base de données
     * @param id La salle à supprimer.
     */
    public void Suppression(int id ) {
        try {
            Connection connection = ((MySQL_Connector)connector).connect();
            Statement statement = connection.createStatement();
            String query="DELETE FROM salles WHERE id_salle='"+ id +"'";

            if(statement.executeUpdate(query) == 1)
                salles.remove(getSalle(id));

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** Getters permettant de retourner les salles. **/
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

