package Seances;

import Film.Film;
import Salles.Salle;
import sample.BDConnector;

public class Seance {

    private int id_seance;
    private Film f;
    private Salle s;
    int nbRes=0;
    private String dateS;
    private String heureDebut;
    private String heureFin;

    /** constructeur pour créer une seance
     *
     * @param _f  le film à ajouter
     * @param _s  la salle de diffusion
     * @param _heureDebut heure de debut du film
     * @param _heureFin   heure de fin du film
     */
    public Seance(int id_seance_, String date_s, Film _f,Salle _s,String _heureDebut,String _heureFin, int nbRes_) {
        nbRes = nbRes_;
        id_seance = id_seance_;
        dateS=date_s;
        f=_f;
        s=_s;
        setHeureDebut(_heureDebut);
        setHeureFin(_heureFin);
    }


    /**
     * Reservation de la seance avec le tarif passe en argument
     * @param t le tarif de reservation
     * @return 'true' Si la reservation s'est bien passe 'false' sinon
     */
    public boolean reserver(Tarif t) {
        if(this.nbRes<this.s.getCapacite()){

            double prix = t.getPrix();
            String type=t.getType();

            nbRes++;

            try {

                BDConnector.connect();
                String requete="INSERT INTO reservation(id_seance,tarif,type) values('"+id_seance+"','"+prix+"','"+type+"')";
                if(BDConnector.st.executeUpdate(requete)== 1) {
                	 requete="UPDATE seances SET nb_reservation = nb_reservation + 1 WHERE id_seance="+id_seance;
                     BDConnector.st.executeUpdate(requete);
                    

                     BDConnector.st.close();
                     return true ;	
                } ;
                
              
            } catch (Exception e) {
                e.printStackTrace();
               
            }
              
        }
        return false ;
    }

    /** getters et settes */
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

    public int getNbRes() {
        return nbRes;
    }

    public void setNbRes(int nbRes) {
        this.nbRes = nbRes;
    }
}
