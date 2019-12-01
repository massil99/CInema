package Film;

import Film.Film;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe permettant d'interagire avec la table Film de la base de donnee
 */
public class ListeFilms {
    private static Connection cnx;
    private static Statement st;
    Queue<Film> films;

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

    /**
     * Constructeur de la classe permettant de charger tous les films de la table Film
     * dans une structure de donnee type FIFO (File)
     */
    public ListeFilms() {
        try {
            connect();
            ResultSet res = st.executeQuery("SELECT * FROM films");

            films = new LinkedList<>();
            while(res.next()){
                films.add( new Film(res.getString("titre"),
                        res.getString("realisateur"),
                        res.getString("date_sortie"),
                        res.getString("categorie"),
                        res.getString("Date_publi"),
                        res.getString("descriptif")));
            }
            res.close();
            st.close();
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
            connect();
            String query = "INSERT INTO films (titre, realisateur,date_Sortie,categorie,  Date_Publi ,descriptif) VALUES"
                    + " ('"+ f.getTitre() +
                    "','"+f.getRealisateur()+
                    "','"+f.getDate_sortie()+
                    "','"+f.getCategorie()+
                    "','"+f.getDate_publi()+
                    "','"+f.getDescriptif()+"')";

            films.add(f);

            st.executeUpdate(query);
            System.out.println("filme ajouter");
        }catch( Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifier les information d'un film indentifier par sont titre
     * @param f : La nouvelle version des informations du film
     * @param titre : Titre du film a modifier
     */
    public void Modifier(Film f, String titre) {
        try {
            connect();
            String query= "UPDATE films SET titre='"+f.getTitre()
                    +"',realisateur='"+f.getRealisateur()
                    +"',Date_date_sortie='"+f.getDate_sortie()
                    +"',categorie='"+f.getCategorie()
                    +"',Date_publi='"+f.getDate_publi()
                    +"',descriptif='"+f.getDescriptif()
                    +"' WHERE titre="+titre;

            films.remove(getfilm(f.getTitre()));
            films.add(f);
            st.executeUpdate(query);
            System.out.println("produit bien modifier");

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
            connect();
            String query="DELETE FROM films WHERE titre='"+titre+"'";

            films.remove(getfilm(titre));

            st.executeUpdate(query);
            System.out.println("succes");
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
     * Renvoie une file de film ayant la meme categorie
     * @param categorie
     */
    public void getFilmByCategorie(String categorie) {
        try {
            connect();
            ResultSet res = st.executeQuery("SELECT * FROM films WHERE categorie='"+categorie+"'");

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
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Getter **/
    public Queue<Film> getFilms() {
        return films;
    }
}

