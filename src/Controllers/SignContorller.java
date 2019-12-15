package Controllers;

import GestionUtilisateur.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SignContorller {

    public TextField lastnameInputSignup;
    public TextField nameInputSignup;
    public TextField usernameInputSignup;
    public PasswordField passInputSignup;
    public PasswordField ConfirmPassInputSignup;
    public VBox SigninScreen;
    public ComboBox<String> type;

    /**
     * Méthode newUser
     * Test et enregistrement des informations saisies par le nouvel utilisateur
     * et affichage du écran de login.
     * @param e : Événement ayant lieu lorsque le bouton 'Sign in' est cliqué.
     */
    public void newUser(ActionEvent e) {
        String username = usernameInputSignup.getText();
        String pass = passInputSignup.getText();
        String comfirmPass = ConfirmPassInputSignup.getText();
        String firstname = nameInputSignup.getText();
        String lastName= lastnameInputSignup.getText();
        String personne = type.getSelectionModel().getSelectedItem();


        if(!pass.equals("")          &&
                pass.equals(comfirmPass) &&
                !username.equals("")     &&
                !lastName.equals("")){
            Utilisateur u = new Utilisateur(lastName, firstname, username, pass, personne);

            u.inscrit();

            Main.changeWindow(e, "../xml/LoginScreen.fxml");
        }else{
            Label wrongCred = new Label("Information invalie !");
            wrongCred.getStyleClass().add("errorMsg");
            SigninScreen.getChildren().add(wrongCred);
        }
    }

    /**
     * Méthode backHome
     * Retour vers le écran de login avec le bouton 'back'.
     * @param e : Événement ayant lieu lorsque le bouton 'back' est cliqué.
     */
    public void backHome(ActionEvent e)  {
        Main.changeWindow(e, "../xml/LoginScreen.fxml");
    }
}
