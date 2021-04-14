package Salles;

public class FabriqueDeSalles extends FabriqueDeSallesAbstraite {

    @Override
    public Salle creerSalle(int numeroSalle_, int capacite_, int nombreDepersonnes_, boolean estDispo_) {
        return new Salle(numeroSalle_, capacite_, nombreDepersonnes_, estDispo_);
    }
}
