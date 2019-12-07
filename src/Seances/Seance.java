package Seances;

import Film.Film;
import Salles.Salle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Seance {

    /** Le film diffusé */
    private Film f;
    /** Salle où le film sera diffusé */
    private Salle s;
    /** Date de diffusion du film*/
    private String dateS;
    /** Heure de debut de la séance*/
    private String heureDebut;
    /** Heure de fin de la séance*/
    private String heureFin;

    /** Attribut de connexion à la base de données*/
    private static Connection cnx;
    private static Statement st;


    private static void connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/cinema?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        cnx = DriverManager.getConnection(url, "root", "root");
        st = cnx.createStatement();
    }

    /** constructeur pour créer une seance
     *
     * @param _f  le film à ajouter
     * @param _s  la salle de diffusion
     * @param _heureDebut heure de debut du film
     * @param _heureFin   heure de fin du film
     */
    public Seance(String date_s, Film _f,Salle _s,String _heureDebut,String _heureFin) {
        dateS=date_s;
        f=_f;
        s=_s;
        setHeureDebut(_heureDebut);
        setHeureFin(_heureFin);
    }

    public void reserver(int idSeance,Tarif t) {
        if(s.isEstDispo()){
            double prix = t.getPrix();
            String type=t.getType();

            s.setNombreDepersonnes(s.getNombreDepersonnes()+1);
            if(s.getNombreDepersonnes() >= s.getCapacite())
                s.setEstDispo(false);

            try {
                connect();
                String requete="INSERT INTO reservation(id_seance,tarif,type) values('"+idSeance+"','"+prix+"','"+type+"')";
                st.executeUpdate(requete);
                String query ="UPDATE salles  SET nombreDePersonnes = (nombreDePersonnes + 1) WHERE id_salle = (select id_salle from seances where id_seance="+idSeance+")";

                st.executeUpdate(query);
                System.out.println("la seance a été reservée.");
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Film getF() {
        return f;
    }

    public void setF(Film f) {
        this.f = f;
    }


    public Salle getS() {
        return s;
    }

    public void setS(Salle s) {
        this.s = s;
    }

    public String getDate() {
        return dateS;
    }

    public void setDate(String date) {
        this.dateS = date;
    }


    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }


    public String getHeureDebut() {
        return heureDebut;
    }


    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
}
