package Seances;

import Controllers.Controller;
import Film.ListeFilms;
import Film.Film;
import Salles.ListeSalles;
import Salles.Salle;
import sample.BDConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

public class ListeSeances {
    /** Liste des seance **/
    ArrayList<Seance> seances;

    /**
     *
     */
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
     * @param date_s Date de la sceance
     * @param _f Film projeté
     * @param _s La salle qui sera occuppée
     * @param _heureDebut Heure de debut
     * @param _heureFin Heure de fin
     * @param nbRes_ Nombre de reservations
     */
    public void Ajouter(String date_s, Film _f,Salle _s,String _heureDebut,String _heureFin, int nbRes_) {
        try {
            BDConnector.connect();

            String query = "SELECT id_seance FROM seances WHERE date='"+date_s+"' AND id_salle= "+ _s.getNumeroSalle() +" AND heure_debut between '"+ _heureDebut+"' AND '"+_heureFin+"'";

            ResultSet res = BDConnector.st.executeQuery(query);
            if(!res.next()) {
                query = "INSERT INTO seances(date,heure_debut,heure_fin, id_film, id_salle, nb_reservation) VALUES ('" + date_s +
                        "','" + _heureDebut +
                        "','" + _heureFin +
                        "','" + _f.getId_film() +
                        "','" + _s.getNumeroSalle() +
                        "',0)";

                if (BDConnector.st.executeUpdate(query) == 1){
                    query = "SELECT * From seances WHERE date='" + date_s +
                            "' AND heure_debut='" + _heureDebut +
                            "' AND heure_fin='" + _heureFin +
                            "' AND id_film=" + _f.getId_film()+
                            "  AND id_salle=" + _s.getNumeroSalle();

                    res =  BDConnector.st.executeQuery(query);
                    if(res.next())
                        seances.add(new Seance(res.getInt("Id_seance"),
                                res.getString("date"),
                                Controller.lf.getfilmById(res.getInt("id_film")),
                                Controller.lsl.getSalle(res.getInt("id_salle")),
                                _heureDebut, _heureFin, 0));
                }
            }
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

            if(BDConnector.st.executeUpdate(query) == 1) {
                Seance se = null;
                for (Seance s : seances)
                    if (s.getId_seance() == idSeance) {
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

    /**
     * @param s
     */
    public static void updateSeance(ArrayList<Seance> s){
        LocalDateTime now = LocalDateTime.now();

        ArrayList<Seance> toRemove = new ArrayList<>();
        ListIterator<Seance> i = s.listIterator();
        while(i.hasNext()){
            Seance seance = i.next();
            int y = Integer.parseInt(seance.getDate(), 0, 4, 10);
            int m = Integer.parseInt(seance.getDate(), 5, 7, 10);
            int j = Integer.parseInt(seance.getDate(), 8, 10, 10);

            if(y < now.getYear()){
                toRemove.add(seance);
            }else  if(y == now.getYear() && m < now.getMonth().getValue()){
                toRemove.add(seance);
            }else if(y == now.getYear() && m == now.getMonth().getValue() && j < now.getDayOfMonth()){
                toRemove.add(seance);
            }
        }
        s.removeAll(toRemove);
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
