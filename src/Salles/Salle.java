package Salles;

public class Salle extends SalleAbstrait {
    /** 
     * Constructeur Salle 
     * Permet de cr�er des nouveaux �l�ments de type Salle.
     * @param numeroSalle_ Le num�ro de la salle cr��e.
     * @param capacite_ La capacit� de la salle cr��e.
     * @param nombreDepersonnes_ Le nombre de personnes dans la salle cr��e.
     * @param estDispo_ La disponibilit� de la salle cr��e.
     * @since 1.0.
     */
    public Salle(int numeroSalle_, int capacite_, int nombreDepersonnes_, boolean estDispo_) {
        super(numeroSalle_, capacite_, nombreDepersonnes_, estDispo_);
    }
    
    /**
     * Constructeur alternatif Salle
     * Permet de cr�er des �l�ments de type Salle vides.
     * @param capacite_ La capacit� de la salle cr��e.
     * @since 2.0.
     */
    public Salle(int capacite_) {
        super(-1, capacite_, 0, true);
    }
}