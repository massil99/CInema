package Film;

public class Film {
    /** Titre du film. */
    private String titre;
    /** Nom du réalisateur du film. */
    private String realisateur;
    /** Date de sortie du film. */
    private String date_sortie;
    /** Nom de la catégorie du film. */
    private String categorie;
    /** Date de publication du film. */
    private String Date_publi;
    /** Description du film. */
    private String descriptif;
    /** Identifiant du film. */
    private int id_film;

    /**
     * Constructeur Film
     * Permet de créer un nouvel élément Film.
     * @param _titre Titre du film.
     * @param _realisateur Nom du réalisateur du film.
     * @param _date_sortie Date de sortie du film.
     * @param _categorie Nom de la catégorie du film.
     * @param _Date_publi Date de publication du film.
     * @param _descriptif Description du film.
     */
    public Film(String _titre,String _realisateur, String _date_sortie,String _categorie, String _Date_publi, String _descriptif) {
        titre=_titre;
        realisateur=_realisateur;
        date_sortie=_date_sortie;
        categorie=_categorie;
        Date_publi=_Date_publi;
        descriptif=_descriptif;
    }

    /** Getters et setters permettant de retourner les valeurs ou les modifier */
    
    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getDate_publi() {
        return Date_publi;
    }

    public void setDate_publi(String date_publi) {
        Date_publi = date_publi;
    }

    public String getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }
}