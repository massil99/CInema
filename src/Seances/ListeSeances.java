package Seances;

import Film.ListeFilms;
import Film.Film;
import Salles.ListeSalles;
import Salles.Salle;
import sample.BDConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

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
                Film f = lf.getfilmById(res.getInt("id_film"));
                Salle s = ls.getSalle(res.getInt("id_salle"));

                seances.add(new Seance(res.getInt("id_seance"), res.getString("date"), f, s, res.getString("heure_debut"), res.getString("heure_fin"), res.getInt("nb_reservation")));
            }
            res.close();
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * methode d'ajout d'une seance dans la base de donnee
     *  @param s l'objet seance a travers lequel la seance est ajouter
     * */
    public void Ajouter(Seance s) {
        try {
            BDConnector.connect();
            String query = "INSERT INTO seances(date,heure_debut,heure_fin, id_film, id_salle, nb_reservation) VALUES ('"+s.getDate()+
                    "','"+s.getHeureDebut()+
                    "','"+s.getHeureFin()+
                    "','"+s.getF().getId_film()+
                    "','"+s.getS().getNumeroSalle()+
                    "','"+s.getNbRes()+"')";

            if(BDConnector.st.executeUpdate(query) == 1)
                seances.add(s);

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
                    +"',nb_reservation="+c.getNbRes()
                    +" WHERE id_seance="+idSeance;

            if(BDConnector.st.executeUpdate(query) == 1) {
                for (Seance s : seances)
                    if (s.getId_seance() == idSeance) {
                        seances.remove(s);
                        break;
                    }
                seances.add(c);
            }
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

            if(BDConnector.st.execute(query)) {
                for (Seance s : seances)
                    if (s.getId_seance() == idSeance) {
                        seances.remove(s);
                        break;
                    }
            }

            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateSeance(){
        LocalDateTime now = LocalDateTime.now();
        String today = String.format("%d-%d-%d", now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());

        try {
            BDConnector.connect();
            String q = "DELETE FROM seances where date<'"+today+"'";
            BDConnector.st.executeUpdate(q);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Seance> getSeanceByFilm(String titre){
        ArrayList<Seance> s = new ArrayList<>();
        for(Seance ss : seances) {
            if (ss.getF().getTitre().equals(titre))
                s.add(ss);
        }

        return s;
    }

    public HashSet<String> getProjectionDays(){
        HashSet<String> d = new HashSet<>();
        for(Seance s : seances)
            d.add(s.getDate());
        return d;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }

    public ArrayList<Seance> getSeancesByDay(String d) {
        ArrayList<Seance> s = new ArrayList<>();
        for(Seance ss: seances)
            if(ss.getDate().equals(d))
                s.add(ss);
        return s;
    }

    public ArrayList<Seance> getSeancesByCategorie(String cat) {
        ArrayList<Seance> s = new ArrayList<>();

        for(Seance ss: seances){
            if(ss.getF().getCategorie().equals(cat))
                s.add(ss);
        }

        return s;
    }
}
