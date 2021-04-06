package Salles;

public class GrandTheatre extends DecorateurSalle {

    public GrandTheatre(int capacite_) {
        super(capacite_);
    }

    @Override
    public int getCapacity() {
        return s.getCapacite()+20;
    }
}
