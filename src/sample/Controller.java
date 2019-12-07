package sample;

import Film.Film;
import Film.ListeFilms;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {
    public TextField usernameInput;
    public PasswordField passInput;

    public TextField lastnameInputSignup;
    public TextField nameInputSignup;
    public TextField usernameInputSignup;
    public PasswordField passInputSignup;
    public PasswordField ConfirmPassInputSignup;

    public StackPane film1;
    public Label title1;
    public Text synopsis1;
    public StackPane film2;
    public Label title2;
    public Text synopsis2;
    public StackPane film3;
    public Label title3;
    public Text synopsis3;
    public StackPane film4;
    public Label title4;
    public Text synopsis4;

    public GridPane loginScreen;

    Utilisateur u;
    ListeFilms lf = new ListeFilms();
    Queue<Film> qf = new LinkedList<>(lf.getFilms());

    /** Defilement à droite et mise à jour des contenaire d'image de film
     * leur titre et leur description
     */
    public void nextFilm(){
        Film f = qf.poll();
        qf.add(f);
        Image img = new Image("res/"+f.getTitre());
        ImageView bg = new ImageView(img);

        film4.getChildren().removeAll(film4.getChildren());
        if(!film3.getChildren().isEmpty())
            film4.getChildren().add(film3.getChildren().get(0));
        title4.setText(title3.getText());

        film3.getChildren().removeAll(film3.getChildren());
        if(!film2.getChildren().isEmpty())
            film3.getChildren().add(film2.getChildren().get(0));
        title3.setText(title2.getText());

        film2.getChildren().removeAll(film2.getChildren());
        if(!film1.getChildren().isEmpty())
            film2.getChildren().add(film1.getChildren().get(0));
        title2.setText(title1.getText());

        film1.getChildren().removeAll(film1.getChildren());
        film1.getChildren().add(bg);
        title1.setText(f.getTitre());
    }

    /** Defilement à gauche mise à jour des contenaire d'image de film
     * leur titre et leur description
     */
    public void prevFilm(){
        Film f = qf.poll();
        qf.add(f);
        Image img = new Image("res/"+f.getTitre());
        ImageView bg = new ImageView(img);

        film1.getChildren().removeAll(film1.getChildren());
        if(!film3.getChildren().isEmpty())
            film1.getChildren().add(film2.getChildren().get(0));
        title1.setText(title2.getText());

        film2.getChildren().removeAll(film2.getChildren());
        if(!film3.getChildren().isEmpty())
            film2.getChildren().add(film3.getChildren().get(0));
        title2.setText(title3.getText());

        film3.getChildren().removeAll(film3.getChildren());
        if(!film4.getChildren().isEmpty())
            film3.getChildren().add(film4.getChildren().get(0));
        title3.setText(title4.getText());

        film4.getChildren().removeAll(film4.getChildren());
        film4.getChildren().add(bg);
        title4.setText(f.getTitre());
    }

    public void showDesc1(){
        Film f = lf.getfilm(title1.getText());
        synopsis1.setText(f.getDescriptif());
    }
    public void hideDesc1(){
        synopsis1.setText("");
    }

    public void showDesc2(){
        Film f = lf.getfilm(title2.getText());
        synopsis2.setText(f.getDescriptif());
    }
    public void hideDesc2(){
        synopsis2.setText("");
    }

    public void showDesc3(){
        Film f = lf.getfilm(title3.getText());
        synopsis3.setText(f.getDescriptif());
    }
    public void hideDesc3(){
        synopsis3.setText("");
    }

    public void showDesc4(){
        Film f = lf.getfilm(title4.getText());
        synopsis4.setText(f.getDescriptif());
    }
    public void hideDesc4(){
        synopsis4.setText("");
    }

    /**
     * Authentification de l'utilisateur et changement de secne
     * @param e : evenement ayant eu lieu lorsque le boutton 'log in' est appuyé
     * @throws IOException : Si une erreur survient lors du charmgement de la scene
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
     * @param e : evenement ayant eu lieu lorsque le boutton 'New user' est appuyé
     * @throws IOException
     */
    public void redirectToSignIn(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../xml/Sign.fxml"));
        Scene signInScene = new Scene(root,  1300, 700);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    /**
     * Test et enregistrement des information saisies par le nouvel utilisatuer
     * et affichage du login screen
     * @param e : evenement ayant eu lieu lorsque le boutton 'Sign in' est appuyé
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    public void newUser(ActionEvent e) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        String username = usernameInputSignup.getText();
        String pass = passInputSignup.getText();
        String comfirmPass = ConfirmPassInputSignup.getText();
        String firstname = nameInputSignup.getText();
        String lastName= lastnameInputSignup.getText();

        if(!pass.equals("")          &&
            pass.equals(comfirmPass) &&
            !username.equals("")     &&
            !lastName.equals("")){
            Utilisateur u = new Utilisateur(lastName, firstname, username, pass);

            Utilisateur.inscrit(u);

            Parent root = FXMLLoader.load(getClass().getResource("../xml/LoginScreen.fxml"));
            Scene signInScene = new Scene(root,  1300, 700);
            Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            window.setScene(signInScene);
            window.show();
        }
    }

    /**
     *  Retour vers le login screen avec le boutton 'back'
     * @param e : evenement ayant eu lieu lorsque le boutton 'Back' est appuyé
     * @throws IOException
     */
    public void backHome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../xml/LoginScreen.fxml"));
        Scene signInScene = new Scene(root,  1300, 700);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }
}
