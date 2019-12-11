package Seances;

import Film.Film;
import Salles.Salle;
import sample.BDConnector;

public class Seance {

    private int id_seance;
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

    /** constructeur pour créer une seance
     *
     * @param _f  le film à ajouter
     * @param _s  la salle de diffusion
     * @param _heureDebut heure de debut du film
     * @param _heureFin   heure de fin du film
     */
    public Seance(int id_seance_, String date_s, Film _f,Salle _s,String _heureDebut,String _heureFin) {
        id_seance = id_seance_;
        dateS=date_s;
        f=_f;
        s=_s;
        setHeureDebut(_heureDebut);
        setHeureFin(_heureFin);
    }

    public void reserver(Tarif t) {
        if(s.isEstDispo()){
            double prix = t.getPrix();
            String type=t.getType();

            s.setNombreDepersonnes(s.getNombreDepersonnes()+1);
            if(s.getNombreDepersonnes() >= s.getCapacite())
                s.setEstDispo(false);

            try {
                BDConnector.connect();
                String requete="INSERT INTO reservation(id_seance,tarif,type) values('"+id_seance+"','"+prix+"','"+type+"')";
                BDConnector.st.executeUpdate(requete);
                String query ="UPDATE salles  SET nombreDePersonnes = (nombreDePersonnes + 1) WHERE id_salle = (select id_salle from seances where id_seance="+id_seance+")";

                BDConnector.st.executeUpdate(query);
                BDConnector.st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getId_seance() {
        return id_seance;
    }

    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
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
