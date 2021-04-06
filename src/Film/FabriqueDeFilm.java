package Film;

public class FabriqueDeFilm extends FabriqueDeFilmAbstraite {


	@Override
	public Film creerFilm(String _titre, String _realisateur, String _date_sortie, String _categorie,
			String _Date_publi, String _descriptif) {
		
		return new Film (_titre,_realisateur,_date_sortie, _categorie, _Date_publi, _descriptif);
	}

}
