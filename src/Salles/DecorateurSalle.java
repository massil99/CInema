package Salles;

public abstract class DecorateurSalle extends SalleAbstrait {
    protected SalleAbstrait s;

    public DecorateurSalle(SalleAbstrait salle, int capacite_) {
        super(capacite_);
        s = salle;
    }

    public abstract int getCapacity();
}
