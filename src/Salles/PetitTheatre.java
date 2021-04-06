package Salles;

public class PetitTheatre extends DecorateurSalle{

    public PetitTheatre(int capacite_) {
        super(capacite_);
    }

    @Override
    public int getCapacity() {
        return s.getCapacite()+10;
    }
}
