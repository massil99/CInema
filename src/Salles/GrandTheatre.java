package Salles;

public class GrandTheatre extends DecorateurSalle {

    public GrandTheatre(SalleAbstrait salle, int capacite_) {
        super(salle, capacite_);
    }

    @Override
    public int getCapacity() {
        return s.getCapacite() + this.getCapacite();
    }
}
