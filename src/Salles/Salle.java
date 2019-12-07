package Salles;

import java.sql.*;

public class Salle {
    private static int count=0;
    private int numeroSalle;
    private int capacite;
    private int nombreDepersonnes=0;
    private  boolean estDispo;

    private static Connection cnx;
    private static Statement st;

    private static void connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/cinema?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        cnx = DriverManager.getConnection(url,"root","root");
        st = cnx.createStatement();
    }

    public Salle(int numeroSalle_, int capacite_, int nombreDepersonnes, boolean estDispo_) {
        numeroSalle = numeroSalle_;
        capacite = capacite_;
        estDispo = estDispo_;
    }

    public Salle(int capacite_) {
        numeroSalle = count;
        count ++;
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
