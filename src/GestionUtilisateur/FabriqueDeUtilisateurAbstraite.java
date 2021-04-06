package GestionUtilisateur;


public abstract class FabriqueDeUtilisateurAbstraite {

	 public abstract Utilisateur getUser(String nom, String prenom, String login, String type);
	 public abstract Utilisateur getUserAvecMdp(String nom, String prenom, String login, String mot_de_passe, String type);
	 
}
