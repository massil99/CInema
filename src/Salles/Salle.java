package Salles;

public class Salle {
    private int numeroSalle;
    private int capacite;
    private int nombreDepersonnes = 0;
    private boolean estDispo;

    /** 
     * Constructeur Salle 
     * Permet de creer des nouveaux elements de type Salle.
     * @param numeroSalle_ Le numero de la salle creee.
     * @param capacite_ La capacite de la salle creee.
     * @param nombreDepersonnes_ Le nombre de personnes de la salle creee.
     * @param estDispo_ La disponibilite de la salle creee.
     */
    
    public Salle(int numeroSalle_, int capacite_, int nombreDepersonnes_, boolean estDispo_) {
        numeroSalle = numeroSalle_;
        capacite = capacite_;
        estDispo = estDispo_;
        nombreDepersonnes = nombreDepersonnes_;
    }
    
    /**
     * Constructeur alternatif Salle
     * Permet de creer des elements de type Salle vide.
     * @param capacite_ La capacite de la salle creee.
     * @since 2.0.
     */
    
    public Salle(int capacite_) {
        numeroSalle = -1;
        capacite = capacite_;
        estDispo = true;
    }
    
    /**
     * Methode getNumeroSalle
     * Permet de retourner le numero de la salle.
     * @return Le numero de la salle.
     * @since 1.0.
     */

    public int getNumeroSalle() {
        return numeroSalle;
    }
    
    /**
     * Methode setNumeroSalle
     * Permet de modifier le numero de la salle.
     * @param numero Le numero de la salle voulu.
     * @since 1.0.
     */

    public void setNumeroSalle(int numero) {
        numeroSalle = numero;
    }
    
    /**
     * Methode getCapacite
     * Permet de retourner la capacite de la salle.
     * @return La capacite de la salle.
     * @since 1.0.
     */

    public int getCapacite() {
        return capacite;
    }
    
    /**
     * Methode setCapacite
     * Permet de modifier la capacite de la salle.
     * @param capacite_ La capacite de la salle voulue.
     * @since 1.0.
     */

    public void setCapacite(int capacite_) {
        capacite = capacite_;
    }
    
    /**
     * Methode isEstDispo
     * Permet de retourner la disponibilite de la salle.
     * @return La disponibilite de la salle.
     * @since 1.0.
     */

    public boolean isEstDispo() {
        return estDispo;
    }
    
    /**
     * Methode setEstDispo
     * Permet de modifier la disponibilite de la salle.
     * @param dispo La disponibilite de la salle voulue.
     * @since 1.0.
     */

    public void setEstDispo(boolean dispo) {
        estDispo = dispo;
    }
    
    /**
     * Methode getNombreDepersonnes
     * Permet de retourner le nombre de personnes de la salle.
     * @return Le nombre de personnes de la salle.
     * @since 1.0.
     */

    public int getNombreDepersonnes() {
        return nombreDepersonnes;
    }

    /**
     * Methode setNumeroSalle
     * Permet de modifier le nombre de personnes de la salle.
     * @param nombreDepersonnes Le nombre de personnes de la salle voulu.
     * @since 1.0.
     */
    
    public void setNombreDepersonnes(int nombreDepersonnes) {
        this.nombreDepersonnes = nombreDepersonnes;
    }
}