package GestionUtilisateur;

import sample.BDConnector;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Classe representant un utlisateur du logiciel
 */
public class Utilisateur {

	private String nom;
	private String prenom;
	private String login;
	private String mot_de_passe;
	private String salt;
	private String type;

    public Utilisateur(String nom, String prenom, String login, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.type = type;
        mot_de_passe = "";
    }

    /**
     * Creation d'un nouvelle utilisatuer
     * @param nom : Nom de l'uilisatuer
     * @param prenom : Prenom de l'utilisateur
     * @param login : Identifant
     * @param mot_de_passe : Mot de passe
     */
	public Utilisateur(String nom, String prenom, String login, String mot_de_passe, String type) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.type = type;

		SecureRandom random = new SecureRandom();
		byte[] s = new byte[16];
		random.nextBytes(s);

		KeySpec spec = new PBEKeySpec(mot_de_passe.toCharArray(), s, 65536, 128);
		SecretKeyFactory factory = null;
		byte[] hash = null;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		try {
			hash = factory.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		Base64.Encoder enc = Base64.getEncoder();
		this.mot_de_passe = enc.encodeToString(hash);
		this.salt = enc.encodeToString(s);
	}


    /**
     * Inscription de l'utilisatuer dans la base de donnee
     */
    public void inscrit() {
        try {
            BDConnector.connect();
            int t = (type.equals("Admin")) ? 1 : (type.equals("Reseptionniste")) ? 2: 3;
            String sql = "insert into utilisateurs (nom, prenom, login, mot_de_passe, salt, type) values('" + nom + "', '" + prenom + "', '" + login + "', '" + mot_de_passe + "', '" + salt +"', "+ t +")";
            BDConnector.st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Teste de la connection de l'utilisateur avec les identifiants donnes
     * @param _login : Identifiant
     * @param _mdp : Mot de passe
     * @return
     */
    public static Utilisateur seConnect(String _login, String _mdp) {
        try {
            BDConnector.connect();
            String sql = "Select nom, prenom, login, mot_de_passe, salt, user from  utilisateurs, personnel where login = '" + _login + "' AND type=id";
            ResultSet rs = BDConnector.st.executeQuery(sql);

            if (rs.next()) {
                Base64.Decoder dec = Base64.getDecoder();
                byte[] s = dec.decode(rs.getString("salt"));

                KeySpec spec = new PBEKeySpec(_mdp.toCharArray(), s, 65536, 128);
                SecretKeyFactory factory = null;
                byte[] hash = null;
                try {
                    factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                try {
                    hash = factory.generateSecret(spec).getEncoded();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }

                Base64.Encoder enc = Base64.getEncoder();
                if(rs.getString("mot_de_passe").equals(enc.encodeToString(hash))){
                    return new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mot_de_passe"), rs.getString("user"));
                }else{
                    return null;
                }

            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

	/** Getters et setters **/
	public ArrayList<Utilisateur> getUsers(){
	    if(type.equals("Admin")){
	        ArrayList<Utilisateur> users = new ArrayList<>();
            try {
                BDConnector.connect();
                String query = "SELECT nom, prenom, login, user FROM utilisateurs, personnel where type=id ORDER BY(type) ";
                ResultSet res = BDConnector.st.executeQuery(query);
                while(res.next()) {
                    users.add(new Utilisateur(res.getString("nom"),
                            res.getString("prenom"),
                            res.getString("login"),
                            res.getString("user")));
                }

                return users;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
	    return null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}