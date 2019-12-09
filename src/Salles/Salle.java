package Salles;

import java.sql.*;

public class Salle {
    private int numeroSalle;
    private int capacite;
    private int nombreDepersonnes=0;
    private boolean estDispo;

    private static Connection cnx;
    private static Statement st;

    public Salle(int numeroSalle_, int capacite_, int nombreDepersonnes_, boolean estDispo_) {
        numeroSalle = numeroSalle_;
        capacite = capacite_;
        estDispo = estDispo_;
        nombreDepersonnes = nombreDepersonnes_;
    }

    public Salle(int capacite_) {
        numeroSalle = -1;
        capacite = capacite_;
        estDispo = true;
    }

    public int getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(int numero) {
        numeroSalle = numero;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite_) {
        capacite = capacite_;
    }

    public boolean isEstDispo() {
        return estDispo;
    }

    public void setEstDispo(boolean dispo) {
        estDispo = dispo;
    }

    public int getNombreDepersonnes() {
        return nombreDepersonnes;
    }

    public void setNombreDepersonnes(int nombreDepersonnes) {
        this.nombreDepersonnes = nombreDepersonnes;
    }
}
