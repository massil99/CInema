package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Film.ListeFilms;

public class ListeFilmsTest {
	
	/** 
	 *  test l'ajout d'un film � la liste des films
	 *  cas valide : les informations du films sont correctes
	 * 
	 */
	private boolean testAjout1(){
		Film.Film f=new Film.Film("La belle","Nicolas","2019-12-01","Drame","2019-11-10"," dwgxfghcbjnazertyuiop");
		ListeFilms lf=new ListeFilms();
		
	
		return lf.Ajout(f)==true;
	}
	
	/**
	 * cas invalide: la date de sortie n'existe pas
	 * autre cas invalide: la connexion � la base de donn�es a echou�
	 * 
	 */
	
	private boolean testAjout2(){
		Film.Film f=new Film.Film("La belle","Nicolas","2019-15-01","Drame","2019-11-10"," dwgxfghcbjnazertyuiop");
		ListeFilms lf=new ListeFilms();
		
	
		return lf.Ajout(f)==false;
	}
	
	/**
	 * test de la methode suppression de  la liste des films
	 * 
	 *  cas valide: le titre du film existe dans la table Films de la base de donn�es
	 *
	 */
	
	private boolean testSuppression1() {
		
        ListeFilms lf=new ListeFilms();
		return lf.Suppression("La belle")==true;
	}
	/**
	 * cas invalide: le titre du film n'existe pas 
	 */
	
	public boolean testSuppression2() {;
	 ListeFilms lf=new ListeFilms();
	 return lf.Suppression("film")==false;
	}
	
	/**
	 * test de la methode modifier 
	 */
	
	/** 
	 * cas valide: l'identifiant existe
	 */
	
	
	public boolean testModifier1() {
		Film.Film f=new Film.Film("La belle","Regie","2019-12-01","Drame","2019-11-10"," dwgxfghcbjnazertyuiop");
		
		ListeFilms lf=new ListeFilms();
		 return lf.Modifier(f,46)==true;
		
	}
	
	/** 
	 * cas invalide: l'identifiant pass� en param�tre n'existe pas 
	 */
	public boolean testModifier2() {
		Film.Film f=new Film.Film("La belle","Regie","2019-12-01","Drame","2019-11-10"," dwgxfghcbjnazertyuiop");
		
		ListeFilms lf=new ListeFilms();
		 return lf.Modifier(f,47)==false;
		
	}
	
	/**
	 * test de la methode getFilm qui retourne un film grace � son titre
	 *
	 * cas valide: le titre pass� en param�tre existe dans la base de donn�es
	 * 
	 */
	public boolean testGetFilm1() {
		ListeFilms lf=new ListeFilms();
		
		return lf.getfilm("Avengers")!=null;
	
	}
	
	/**
	 * 
	 * cas invalide: le titre n'existe pas
	 */
	
	
	public boolean testGetFilm2() {
		ListeFilms lf=new ListeFilms();
		
		return lf.getfilm("titre")==null;
	
	}
	
	/**
	 * test de la methode getFilmById qui retourne le film dont l'identifiant est pass� en param�tre
	 * 
	 * cas valide: l'identifiant existe
	 * 
	 */
	
	public boolean testGetFilmById1() {
		ListeFilms lf=new ListeFilms();
		
		return lf.getfilmById(46)!=null;
	
	}
	/**
	 * 
	 *  cas invalide: l'identifiant n'existe pas 
	 */
	
	
	public boolean testGetFilmById2() {
		ListeFilms lf=new ListeFilms();
		
		return lf.getfilmById(-1)==null;
	
	}
	
	/**
	 * test de la methode getFilmByCategorie qui retourne tous les films de la categorie pass�e en param�tre
	 * 
	 * cas valide: la categorie  existe
	 * 
	 */
	
	public boolean testGetFilmByCategorie1() {
		ListeFilms lf=new ListeFilms();
		
		return lf.getFilmByCategorie("Drame")==true;
	
	}
	/**
	 * 
	 *  cas invalide: la cat�gorie n'existe pas 
	 */
	
	public boolean testGetFilmByCategorie2() {
		ListeFilms lf=new ListeFilms();
		
		return lf.getFilmByCategorie("A")==false;
	}
	

	@Test
	public void test() {
		assertTrue(testGetFilmByCategorie2());
		
	}

}
