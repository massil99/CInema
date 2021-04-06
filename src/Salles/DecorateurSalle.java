package Salles;

public abstract class DecorateurSalle extends Salle {
    public Salle s;

    public DecorateurSalle(int capacite_) {
        super(capacite_);
    }

    public abstract int getCapacity();


}
