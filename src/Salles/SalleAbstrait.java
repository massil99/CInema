package Salles;

public abstract class SalleAbstrait {
    /** Le num�ro de la salle cr��e. */
    protected int numeroSalle;
    /** La capacit� de la salle cr��e. */
    protected int capacite;
    /** Le nombre de personnes dans la salle cr��e. */
    protected int nombreDepersonnes = 0;
    /** La disponibilit� de la salle cr��e. */
    protected boolean estDispo;

    /**
     * Constructeur Salle
     * Permet de cr�er des nouveaux �l�ments de type Salle.
     * @param numeroSalle_ Le num�ro de la salle cr��e.
     * @param capacite_ La capacit� de la salle cr��e.
     * @param nombreDepersonnes_ Le nombre de personnes dans la salle cr��e.
     * @param estDispo_ La disponibilit� de la salle cr��e.
     * @since 1.0.
     */
    public SalleAbstrait(int numeroSalle_, int capacite_, int nombreDepersonnes_, boolean estDispo_) {
        numeroSalle = numeroSalle_;
        capacite = capacite_;
        estDispo = estDispo_;
        nombreDepersonnes = nombreDepersonnes_;
    }

    /**
     * Constructeur alternatif Salle
     * Permet de cr�er des �l�ments de type Salle vides.
     * @param capacite_ La capacit� de la salle cr��e.
     * @since 2.0.
     */
    public SalleAbstrait(int capacite_) {
        numeroSalle = -1;
        capacite = capacite_;
        estDispo = true;
    }

    /**
     * M�thode getNumeroSalle
     * Permet de retourner le num�ro de la salle.
     * @return Le num�ro de la salle.
     * @since 1.0.
     */

    public int getNumeroSalle() {
        return numeroSalle;
    }

    /**
     * M�thode setNumeroSalle
     * Permet de modifier le num�ro de la salle.
     * @param numero Le num�ro de la salle voulu.
     * @since 1.0.
     */

    public void setNumeroSalle(int numero) {
        numeroSalle = numero;
    }

    /**
     * M�thode getCapacite
     * Permet de retourner la capacit� de la salle.
     * @return La capacit� de la salle.
     * @since 1.0.
     */

    public int getCapacite() {
        return capacite;
    }

    /**
     * M�thode setCapacite
     * Permet de modifier la capacit� de la salle.
     * @param capacite_ La capacit� de la salle voulue.
     * @since 1.0.
     */

    public void setCapacite(int capacite_) {
        capacite = capacite_;
    }

    /**
     * M�thode isEstDispo
     * Permet de retourner la disponibilit� de la salle.
     * @return La disponibilit� de la salle.
     * @since 1.0.
     */

    public boolean isEstDispo() {
        return estDispo;
    }

    /**
     * M�thode setEstDispo
     * Permet de modifier la disponibilit� de la salle.
     * @param dispo La disponibilit� de la salle voulue.
     * @since 1.0.
     */

    public void setEstDispo(boolean dispo) {
        estDispo = dispo;
    }

    /**
     * M�thode getNombreDepersonnes
     * Permet de retourner le nombre de personnes dans la salle.
     * @return Le nombre de personnes dans la salle.
     * @since 1.0.
     */

    public int getNombreDepersonnes() {
        return nombreDepersonnes;
    }

    /**
     * M�thode setNumeroSalle
     * Permet de modifier le nombre de personnes dans la salle.
     * @param nombreDepersonnes Le nombre de personnes dans la salle voulu.
     * @since 1.0.
     */

    public void setNombreDepersonnes(int nombreDepersonnes) {

        this.nombreDepersonnes = nombreDepersonnes;
    }

}
