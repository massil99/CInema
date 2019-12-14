package Film;

import sample.BDConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe permettant d'interagire avec la table Film de la base de donnee
 */
public class ListeFilms {
    Queue<Film> films;

    /**
     * Constructeur de la classe permettant de charger tous les films de la table Film
     * dans une structure de donnee type FIFO (File)
     */
    public ListeFilms() {
        try {
            BDConnector.connect();
            ResultSet res = BDConnector.st.executeQuery("SELECT * FROM films");

            films = new LinkedList<>();
            while(res.next()){
                Film f = new Film(res.getString("titre"),
                        res.getString("realisateur"),
                        res.getString("date_sortie"),
                        res.getString("categorie"),
                        res.getString("Date_publi"),
                        res.getString("descriptif"));
                f.setId_film(res.getInt("id_Film"));
                films.add(f);
            }
            res.close();
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajout le film f dans la base de donnee et dans la file
     * @param f : Film à ajouter
     */
    public void Ajout(Film f) {
        try {
            BDConnector.connect();
            String query = "INSERT INTO films (titre, realisateur,date_Sortie,categorie,  Date_Publi ,descriptif) VALUES"
                    + " ('"+ f.getTitre() +
                    "','"+f.getRealisateur()+
                    "','"+f.getDate_sortie()+
                    "','"+f.getCategorie()+
                    "','"+f.getDate_publi()+
                    "','"+f.getDescriptif()+"')";

            if(BDConnector.st.executeUpdate(query) == 1)
                films.add(f);

            query = "Select id_film From films WHERE"
                    + " titre='"+ f.getTitre()+"'";

            ResultSet res = BDConnector.st.executeQuery(query);
            if(res.next())
                f.setId_film(res.getInt(1));

            res.close();
        }catch( Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifier les information d'un film indentifier par sont titre
     * @param f La nouvelle version des informations du film
     * @param titre Titre du film a modifier
     */
    public void Modifier(Film f, String titre) {
        try {
            BDConnector.connect();
            String query= "UPDATE films SET titre='"+f.getTitre()
                    +"',realisateur='"+f.getRealisateur()
                    +"',Date_date_sortie='"+f.getDate_sortie()
                    +"',categorie='"+f.getCategorie()
                    +"',Date_publi='"+f.getDate_publi()
                    +"',descriptif='"+f.getDescriptif()
                    +"' WHERE titre="+titre;

            if(BDConnector.st.executeUpdate(query) == 1) {
                films.remove(getfilm(f.getTitre()));
                films.add(f);
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Supprimer un film de la base de donnee et de la file
     * @param titre : Film a supprimer
     */
    public void Suppression(String titre) {
        try {
            BDConnector.connect();
            String query="DELETE FROM films WHERE titre='"+titre+"'";

            if(BDConnector.st.executeUpdate(query) == 1)
                films.remove(getfilm(titre));

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Chercher un film dans la base de donne en ayant sont titre
     * @param titre
     * @return : le film si tourvé
     */
    public Film getfilm(String titre) {
        for(Film f : films){
            if(f.getTitre().equals(titre))
                return f;
        }
        return null;
    }

    /**
     * Chercher un film dans la base de donne en ayant sont Id
     * @param id du film
     * @return : le film si tourvé
     */
    public Film getfilmById(int id) {
        for(Film f : films){
            if(f.getId_film() == id)
                return f;
        }
        return null;
    }

    /**
     * Renvoie une file de film ayant la meme categorie
     * @param categorie
     */
    public void getFilmByCategorie(String categorie) {
        try {
            BDConnector.connect();
            ResultSet res = BDConnector.st.executeQuery("SELECT * FROM films WHERE categorie='"+categorie+"'");

            films.removeAll(films);
            while (res.next()){
                films.add( new Film(res.getString("titre"),
                        res.getString("realisateur"),
                        res.getString("date_sortie"),
                        res.getString("categorie"),
                        res.getString("Date_publi"),
                        res.getString("descriptif")));
            }

            res.close();
            BDConnector.st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Getter **/
    public Queue<Film> getFilms() {
        return films;
    }
}

