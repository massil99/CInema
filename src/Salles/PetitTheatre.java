package Salles;

public class PetitTheatre extends DecorateurSalle{

    public PetitTheatre(Salle salle, int capacite_) {
        super(salle, capacite_);
    }

    @Override
    public int getCapacity() {
        return s.getCapacite() - this.getCapacite();
    }
}
