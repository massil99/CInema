package Seances;

import Film.ListeFilms;
import Film.Film;
import Salles.ListeSalles;
import Salles.Salle;
import sample.BDConnector;

import java.sql.*;
import java.util.ArrayList;

public class ListeSeances {
    /** Liste des seance **/
    ArrayList<Seance> seances;

    public ListeSeances(){
        try {
            BDConnector.connect();
            ResultSet res = BDConnector.st.executeQuery("SELECT * FROM seances");

            seances = new ArrayList<>();
            ListeFilms lf = new ListeFilms();
            ListeSalles ls = new ListeSalles();
            while(res.next()){
                Film f = lf.getfilmById(res.getString("id_film"));
                Salle s = ls.getSalle(res.getInt("id_salle"));
                seances.add( new Seance(res.getString("date"), f, s, res.getString("heure_debut"), res.getString("heure_fin")));
            }
            res.close();
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * methode d'ajout d'une seance dans la base de donnee
     *  @param c l'objet seance a travers lequel la seance est ajouter
     * */
    public void Ajouter(Seance c) {
        try {
            BDConnector.connect();
            String query = "INSERT INTO seances(date,heure_debut,heure_fin, id_film, id_salle) VALUES ('"+c.getDate()+
                    "','"+c.getHeureDebut()+
                    "','"+c.getHeureFin()+
                    "','"+c.getF().getId_film()+
                    "','"+c.getS().getNumeroSalle()+"')";

            BDConnector.st.executeUpdate(query);
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * methode de modification d'une seance dans la base de donnee
     *  @param c l'objet seance a travers lequel la seance est ajouter
     *  @param idSeance l'identifiant de la seance qui va être modifier
     * */
    public void modifier(Seance c, int idSeance) {
        try {
            BDConnector.connect();
            String query =  "UPDATE seances SET date='"+c.getDate()
                    +"',heure_debut='"+c.getHeureDebut()
                    +"',heure_fin='"+c.getHeureFin()
                    +"',id_film='"+c.getF().getId_film()
                    +"',id_salle='"+c.getS().getNumeroSalle()
                    +"' WHERE id_seance="+idSeance;

            BDConnector.st.executeUpdate(query);
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * methode de suppression d'une seance dans la base de donnee
     *  @param idSeance l'identifiant de la seance qui va être supprimer
     * */
    public void Supprimer(int idSeance){
        try {

            BDConnector.connect();
            String query = "DELETE FROM `seances` WHERE id_seance="+idSeance;

            BDConnector.st.execute(query);
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
