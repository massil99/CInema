package GestionUtilisateur;

public class FabriqueDeUtilisateur extends FabriqueDeUtilisateurAbstraite{

	@Override
	public Utilisateur getUser(String nom, String prenom, String login, String type) {
		 return new Utilisateur(nom, prenom, login,type);
	}

	@Override
	public Utilisateur getUserAvecMdp(String nom, String prenom, String login, String mot_de_passe, String type) {
		// TODO Auto-generated method stub
		return new Utilisateur(nom, prenom, login, mot_de_passe, type);
	}

}
