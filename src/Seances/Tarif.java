package Seances;

/**
 * Énumération Tarif
 * Les différents tarifs disponibles.
 */
public enum Tarif {
    Reduit("Etudiant",7.80),
    MoinsDe14("Enfant",5.50),
    Plein("Adulte",9.80),
    Matin("Matin",6.80);

    private String type="";
    private double prix=0.;

    Tarif(String _type, double _prix){
        type=_type;
        prix=_prix;

    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
