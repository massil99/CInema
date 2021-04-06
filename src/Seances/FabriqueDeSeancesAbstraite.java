package Seances;

import Film.Film;
import Salles.Salle;

public abstract class FabriqueDeSeancesAbstraite {

	public abstract Seance creerSeance(int id_seance_, String date_s, Film _f,Salle _s,String _heureDebut,String _heureFin, int nbRes_);


}
