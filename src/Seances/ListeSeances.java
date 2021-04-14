package Seances;

import Controllers.Controller;
import Film.ListeFilms;
import Film.Film;
import Observateur.ObservateursListeFilms;
import Observateur.Sujet;
import Salles.ListeSalles;
import Salles.Salle;
import sample.Strategy.ConnectorInterface;
import sample.Strategy.MySQL_Connector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import static java.lang.Integer.parseInt;

public class ListeSeances implements ObservateursListeFilms {
    private Sujet listefilms;
    /** Liste des séances. **/
    ArrayList<Seance> seances;
    ConnectorInterface connector;
    /**
     * Constructeur ListeSeances
     * Chargement des séances en local.
     */
    public ListeSeances(){
        listefilms=new ListeFilms();
        listefilms.addObservateur(this);
        try {

          //  Connection connection =  MySQL_Connector.connect();
            this.connector = MySQL_Connector.getInstance();
            Connection connection = ((MySQL_Connector)connector).connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM seances");

            seances = new ArrayList<>();
            ListeFilms lf = new ListeFilms();
            ListeSalles ls = new ListeSalles();
            while(res.next()){
                Film f = lf.getfilmById(res.getInt("id_film"));
                Salle s = ls.getSalle(res.getInt("id_salle"));

                seances.add(new Seance(res.getInt("id_seance"), res.getString("date"), f, s, res.getString("heure_debut"), res.getString("heure_fin"), res.getInt("nb_reservation")));
            }
            res.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Méthode Ajouter
     * Ajoute une seance dans la base de données.
     * @param date_s Date de la séance.
     * @param _f Film projeté.
     * @param _s La salle qui sera occuppée.
     * @param _heureDebut Heure de début.
     * @param _heureFin Heure de fin.
     * @param nbRes_ Nombre de réservations.
     */
    public void Ajouter(String date_s, Film _f,Salle _s,String _heureDebut,String _heureFin, int nbRes_) {
        try {
            String query = "SELECT id_seance FROM seances WHERE date='"+date_s+"' AND id_salle= "+ _s.getNumeroSalle() +" AND heure_debut between '"+ _heureDebut+"' AND '"+_heureFin+"'";

           // Connection connection =  MySQL_Connector.connect();
            Connection connection = ((MySQL_Connector)connector).connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(!res.next()) {
                query = "INSERT INTO seances(date,heure_debut,heure_fin, id_film, id_salle, nb_reservation) VALUES ('" + date_s +
                        "','" + _heureDebut +
                        "','" + _heureFin +
                        "','" + _f.getId_film() +
                        "','" + _s.getNumeroSalle() +
                        "',0)";

                if (statement.executeUpdate(query) == 1){
                    query = "SELECT * From seances WHERE date='" + date_s +
                            "' AND heure_debut='" + _heureDebut +
                            "' AND heure_fin='" + _heureFin +
                            "' AND id_film=" + _f.getId_film()+
                            "  AND id_salle=" + _s.getNumeroSalle();

                    res =  statement.executeQuery(query);
                    if(res.next())
                        seances.add(new Seance(res.getInt("Id_seance"),
                                res.getString("date"),
                                Controller.lf.getfilmById(res.getInt("id_film")),
                                Controller.lsl.getSalle(res.getInt("id_salle")),
                                _heureDebut, _heureFin, 0));
                }
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode modifier
     * Modifie une séance dans la base de données.
     * @param c L'objet séance à travers lequel la séance est ajoutée.
     * @param idSeance L'identifiant de la séance qui va être modifiée.
     */
    public void modifier(Seance c, int idSeance) {
        try {
           // Connection connection =  MySQL_Connector.connect();
            Connection connection = ((MySQL_Connector)connector).connect();
            Statement statement = connection.createStatement();
            String query =  "UPDATE seances SET date='"+c.getDate()
                    +"',heure_debut='"+c.getHeureDebut()
                    +"',heure_fin='"+c.getHeureFin()
                    +"',id_film='"+c.getF().getId_film()
                    +"',id_salle='"+c.getS().getNumeroSalle()
                    +"',nb_reservation="+c.getNbRes()
                    +" WHERE id_seance="+idSeance;

            if(statement.executeUpdate(query) == 1) {
                for (Seance s : seances)
                    if (s.getId_seance() == idSeance) {
                        seances.remove(s);
                        break;
                    }
                seances.add(c);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode Supprimer
     * Supprime une séance de la base de données.
     * @param idSeance L'identifiant de la séance qui va être supprimée.
     */
    public void Supprimer(int idSeance){
        try {
           // Connection connection =  MySQL_Connector.connect();
            Connection connection = ((MySQL_Connector)connector).connect();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM `seances` WHERE id_seance="+idSeance;

            if(statement.executeUpdate(query) == 1) {
                Seance se = null;
                for (Seance s : seances)
                    if (s.getId_seance() == idSeance) {
                        se = s;
                        break;
                    }

                seances.remove(se);
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode updateSeance
     * Supprime les séances ayant une date depassée.

     * @param s
     */
    public static void updateSeance(ArrayList<Seance> s){
        LocalDateTime now = LocalDateTime.now();

        ArrayList<Seance> toRemove = new ArrayList<>();
        ListIterator<Seance> i = s.listIterator();
        while(i.hasNext()){
            Seance seance = i.next();
            String[] date = seance.getDate().split("-");
            int y = Integer.parseInt(date[0]);
            int m = Integer.parseInt(date[1]);
            int j = Integer.parseInt(date[2]);
            if(y < now.getYear()) {
                toRemove.add(seance);
            }else  if(y == now.getYear() && m < now.getMonth().getValue()){
                toRemove.add(seance);
            }else if(y == now.getYear() && m == now.getMonth().getValue() && j < now.getDayOfMonth()){
                toRemove.add(seance);
            }
        }
        s.removeAll(toRemove);
    }

    /** Getters et Setters permettant de retourner les valeurs ou les modifier. */
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

    @Override
    public void update(Film f) {
        try {
            BDConnector.connect();
            String query = "DELETE FROM `seances` WHERE id_film="+ f.getId_film();

            if(BDConnector.st.executeUpdate(query) == 1) {
                Seance se = null;
                for (Seance s : seances)
                    if (s.getF().equals(f)) {
                        se = s;
                        break;
                    }

                seances.remove(se);
            }

            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
