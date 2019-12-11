package GestionUtilisateur;

import sample.BDConnector;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
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

    /**
     * Creation d'un nouvelle utilisatuer
     * @param nom : Nom de l'uilisatuer
     * @param prenom : Prenom de l'utilisateur
     * @param login : Identifant
     * @param mot_de_passe : Mot de passe
     */
	public Utilisateur(String nom, String prenom, String login, String mot_de_passe) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;

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
     * @param u
     */
    public static void inscrit(Utilisateur u) {
        try {
            BDConnector.connect();

            String sql = "insert into utilisateurs (nom, prenom, login, mot_de_passe, salt) values('" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getLogin() + "', '" + u.getMdp() + "', '" + u.salt +"')";
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
            String sql = "Select * from  utilisateurs where login = '" + _login + "'";
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
                    return new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mot_de_passe"));
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
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mot_de_passe;
	}
}