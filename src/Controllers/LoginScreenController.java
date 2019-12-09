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
    public void loginButton(ActionEvent e) throws IOException{
        u = Utilisateur.seConnect(usernameInput.getText(), passInput.getText());

        if(u != null){
            Parent root = FXMLLoader.load(getClass().getResource("../xml/FilmsM.fxml"));
            Scene signInScene = new Scene(root,  1300, 700);
            Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            window.setScene(signInScene);
            window.show();
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
     * @throws IOException
     */
    public void redirectToSignIn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../xml/Sign.fxml"));
        Scene signInScene = new Scene(root,  1300, 700);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }
}
