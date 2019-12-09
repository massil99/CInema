package Film;

public class Film {
    private String titre;
    private String realisateur;
    private String date_sortie;
    private String categorie;
    private String Date_publi;
    private String descriptif;
    private int id_film;

    public Film(String _titre,String _realisateur, String _date_sortie,String _categorie, String _Date_publi, String _descriptif) {
        titre=_titre;
        realisateur=_realisateur;
        date_sortie=_date_sortie;
        categorie=_categorie;
        Date_publi=_Date_publi;
        descriptif=_descriptif;
    }

    /** Getter et Setter **/
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