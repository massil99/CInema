package Salles;

public class Salle {
    
    /** Le numéro de la salle. */
    private int numeroSalle;
    /** La capacité de la salle. */
    private int capacite;
    /** Le nombre de personnes dans la salle. */
    private int nombreDepersonnes = 0;
    /** La disponibilité de la salle. */
    private boolean estDispo;

    /** 
     * Constructeur Salle 
     * Permet de créer des nouveaux éléments de type Salle.
     * @param numeroSalle_ Le numéro de la salle créée.
     * @param capacite_ La capacité de la salle créée.
     * @param nombreDepersonnes_ Le nombre de personnes dans la salle créée.
     * @param estDispo_ La disponibilité de la salle créée.
     * @since 1.0.
     */
    
    public Salle(int numeroSalle_, int capacite_, int nombreDepersonnes_, boolean estDispo_) {
        numeroSalle = numeroSalle_;
        capacite = capacite_;
        estDispo = estDispo_;
        nombreDepersonnes = nombreDepersonnes_;
    }
    
    /**
     * Constructeur alternatif Salle
     * Permet de créer des éléments de type Salle vide.
     * @param capacite_ La capacité de la salle créée.
     * @since 2.0.
     */
    
    public Salle(int capacite_) {
        numeroSalle = -1;
        capacite = capacite_;
        estDispo = true;
    }
    
    /**
     * Méthode getNumeroSalle
     * Permet de retourner le numéro de la salle.
     * @return Le numéro de la salle.
     * @since 1.0.
     */

    public int getNumeroSalle() {
        return numeroSalle;
    }
    
    /**
     * Méthode setNumeroSalle
     * Permet de modifier le numéro de la salle.
     * @param numero Le numéro de la salle voulu.
     * @since 1.0.
     */

    public void setNumeroSalle(int numero) {
        numeroSalle = numero;
    }
    
    /**
     * Méthode getCapacite
     * Permet de retourner la capacité de la salle.
     * @return La capacité de la salle.
     * @since 1.0.
     */

    public int getCapacite() {
        return capacite;
    }
    
    /**
     * Méthode setCapacite
     * Permet de modifier la capacité de la salle.
     * @param capacite_ La capacité de la salle voulue.
     * @since 1.0.
     */

    public void setCapacite(int capacite_) {
        capacite = capacite_;
    }
    
    /**
     * Méthode isEstDispo
     * Permet de retourner la disponibilité de la salle.
     * @return La disponibilité de la salle.
     * @since 1.0.
     */

    public boolean isEstDispo() {
        return estDispo;
    }
    
    /**
     * Méthode setEstDispo
     * Permet de modifier la disponibilité de la salle.
     * @param dispo La disponibilité de la salle voulue.
     * @since 1.0.
     */

    public void setEstDispo(boolean dispo) {
        estDispo = dispo;
    }
    
    /**
     * Méthode getNombreDepersonnes
     * Permet de retourner le nombre de personnes de la salle.
     * @return Le nombre de personnes dans la salle.
     * @since 1.0.
     */

    public int getNombreDepersonnes() {
        return nombreDepersonnes;
    }

    /**
     * Méthode setNumeroSalle
     * Permet de modifier le nombre de personnes de la salle.
     * @param nombreDepersonnes Le nombre de personnes dans la salle voulu.
     * @since 1.0.
     */
    
    public void setNombreDepersonnes(int nombreDepersonnes) {
        this.nombreDepersonnes = nombreDepersonnes;
    }
}