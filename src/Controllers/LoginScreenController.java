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
     * Authentification de l'utilisateur et changement de secne
     * @param e evenement ayant eu lieu lorsque le boutton 'log in' est appuyé
     * @throws IOException Si une erreur survient lors du charmgement de la scene
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
     * Affichage de la fenetre d'inscription du nouvel utilisateur
     * @param e  evenement ayant eu lieu lorsque le boutton 'New user' est appuyé
     */
    public void redirectToSignIn(ActionEvent e) {
        Main.changeWindow(e, "../xml/Sign.fxml");
    }
}
