package Film;

import sample.ConnectorInterface;
import sample.MySQL_Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe ListFilms
 * Classe permettant d'interagir avec la table Film de la base de donn�es.
 */
public class ListeFilms {
    Queue<Film> films;

    /**
     * Constructeur ListeFilms
     * Constructeur de la classe permettant de charger tous les films de la table Film
     * dans une structure de donn�es de type FIFO (File).
     */
    public ListeFilms() {
        try {
            Connection connection =  MySQL_Connector.connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM films");

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
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * M�thode Ajout
     * Ajoute le film f dans la base de donn�es et dans la file.
     * @param f : Film � ajouter.
     */
    public boolean Ajout(Film f) {
        try {
            Connection connection =  MySQL_Connector.connect();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO films (titre, realisateur,date_Sortie,categorie,  Date_Publi ,descriptif) VALUES"
                    + " ('"+ f.getTitre() +
                    "','"+f.getRealisateur()+
                    "','"+f.getDate_sortie()+
                    "','"+f.getCategorie()+
                    "','"+f.getDate_publi()+
                    "','"+f.getDescriptif()+"')";

            if(statement.executeUpdate(query) == 1) {
                films.add(f);

                query = "Select id_film From films WHERE"
	                    + " titre='"+ f.getTitre()+"'";
	
	            ResultSet res = statement.executeQuery(query);
	            
	            res.next();
	            
	            if(res != null)
	            	f.setId_film(res.getInt("id_film"));
            	res.close();  
            	return true;
            }
        }catch( Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * M�thode Modifier
     * Modifie les informations d'un film identifi� par son titre.
     * @param f La nouvelle version des informations du film.
     * @param id_film Identifiant du film � modifier.
     */
    public boolean Modifier(Film f, int id_film) {
        try {
            Connection connection =  MySQL_Connector.connect();
            Statement statement = connection.createStatement();
            String query= "UPDATE films SET titre='"+f.getTitre()
                    +"',realisateur='"+f.getRealisateur()
                    +"',date_Sortie='"+f.getDate_sortie()
                    +"',categorie='"+f.getCategorie()
                    +"',Date_publi='"+f.getDate_publi()
                    +"',descriptif='"+f.getDescriptif()
                    +"' WHERE id_film="+id_film;

            if(statement.executeUpdate(query) == 1) {
                films.remove(getfilm(f.getTitre()));
                films.add(f);
                return true;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * M�thode Suppression
     * Supprime un film de la base de donn�es et de la file
     * @param titre Film � supprimer.
     */
    public boolean Suppression(String titre) {
        try {
            Connection connection =  MySQL_Connector.connect();
            Statement statement = connection.createStatement();
            String query="DELETE FROM films WHERE titre='"+titre+"'";

            if(statement.executeUpdate(query) == 1) {
                films.remove(getfilm(titre));
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * M�thode getfilm
     * Cherche un film dans la base de donn�es en ayant son titre.
     * @param titre Titre du film.
     * @return Le film trouv�.
     */
    public Film getfilm(String titre) {
        for(Film f : films){
            if(f.getTitre().equals(titre))
                return f;
        }
        return null;
    }

    /**
     * M�thode getfilmById
     * Cherche un film dans la base de donn�es en ayant son identifiant
     * @param id Identifiant du film.
     * @return Le film trouv�.
     */
    public Film getfilmById(int id) {
        for(Film f : films){
            if(f.getId_film() == id)
                return f;
        }
        return null;
    }

    /**
     * M�thode getFilmByCategorie
     * Renvoie une file de films ayant la m�me cat�gorie.
     * @param categorie
     */
    public boolean getFilmByCategorie(String categorie) {
        try {
            Connection connection =  MySQL_Connector.connect();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM films WHERE categorie='"+categorie+"'");
            if(res != null) {
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
            statement.close();
            return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * M�thode getFilms
     * Retourne les films de la liste.
     * @return 
     */
    public Queue<Film> getFilms() {
        return films;
    }
}

