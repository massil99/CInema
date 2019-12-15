package Controllers;

import GestionUtilisateur.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class LoginScreenController {
    public TextField usernameInput;
    public PasswordField passInput;
    public GridPane loginScreen;


    public static Utilisateur u;

    /**
     * Méthode loginButton
     * Authentification de l'utilisateur et changement de sens.
     * @param e Événement ayant lieu lorsque le bouton 'log in' est cliqué.
     * @throws IOException Si une erreur survient lors du chargement de la scène.
     */
    public void loginButton(ActionEvent e){
        u = Utilisateur.seConnect(usernameInput.getText(), passInput.getText());

        if(u != null){
            if(!u.getType().equals("Comptable"))
                Main.changeWindow(e, "../xml/FilmsM.fxml");
            else
                Main.changeWindow(e, "../xml/stats.fxml");
        }else{
            Label wrongCred = new Label("Wrong username or passeword!");
            wrongCred.getStyleClass().add("errorMsg");
            GridPane.setConstraints(wrongCred, 0, 5);
            loginScreen.getChildren().add(wrongCred);
        }
    }

    /**
     * Méthode redirectToSignIn
     * Affichage de la fenêtre d'inscription du nouvel utilisateur.
     * @param e  Événement ayant lieu lorsque le bouton 'New user' est cliqué.
     */
    public void redirectToSignIn(ActionEvent e) {
        Main.changeWindow(e, "../xml/Sign.fxml");
    }
}
