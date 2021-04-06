package Seances;

import Film.Film;
import Salles.Salle;

public class FabriqueDeSeances extends FabriqueDeSeancesAbstraite{

	@Override
	public Seance creerSeance(int id_seance_, String date_s, Film _f, Salle _s, String _heureDebut, String _heureFin,
			int nbRes_) {
		return new Seance(id_seance_, date_s, _f, _s,_heureDebut, _heureFin, nbRes_);
	}

}
