package Controllers;

import GestionUtilisateur.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.BDConnector;
import sample.ConfermBox;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsersBoardController implements Initializable {
    public VBox listeU;
    ArrayList<Utilisateur> users = LoginScreenController.u.getUsers();

    /**
     * M�thode initialize
     * Chargement de la liste des comptes enregistr�s et les boutons de suppression
     * pour les comptes non-admins.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Utilisateur u : users) {
            HBox user = new HBox();
            user.getStyleClass().add("item");
            user.setSpacing(10);

            VBox v1 = new VBox();
            Label lnom = new Label("Nom");
            lnom.getStyleClass().add("infoLabel");
            Label nom = new Label(u.getNom());
            v1.getChildren().addAll(lnom, nom);

            VBox v2 = new VBox();
            Label lprenom = new Label("Prenom");
            lprenom.getStyleClass().add("infoLabel");
            Label prenom = new Label(u.getPrenom());
            v2.getChildren().addAll(lprenom, prenom);

            VBox v3 = new VBox();
            Label llog = new Label("Login");
            llog.getStyleClass().add("infoLabel");
            Label login = new Label(u.getLogin());
            v3.getChildren().addAll(llog, login);

            VBox v4 = new VBox();
            Label lcompte = new Label("Compte");
            lcompte.getStyleClass().add("infoLabel");
            Label compte = new Label(u.getType());
            v4.getChildren().addAll(lcompte, compte);

            VBox v = new VBox();
            v.setSpacing(5);
            v.setAlignment(Pos.CENTER);

            if (!u.getType().equals("Admin")) {
                Button del = new Button("Supprimer");
                del.setOnAction(e -> {
                    if (ConfermBox.display("Suppression", "Voulez vous supprimmer ce compte : " + u.getLogin())) {
                        try {
                            Connection connection =  BDConnector.connect();
                            Statement statement = connection.createStatement();
                            String q = "DELETE FROM utilisateurs where login='" + u.getLogin() + "'";
                            if (statement.executeUpdate(q) == 1) {
                                int i = 0;
                                for (i = 0; i < users.size(); i++){
                                    String l = ((Label) ((VBox) ((HBox) ((HBox) listeU.getChildren().get(i)).getChildren().get(0)).getChildren().get(2)).getChildren().get(1)).getText();
                                    if (l.equals(u.getLogin()))
                                        break;
                                }
                                if(i < users.size() )
                                    listeU.getChildren().remove(i);
                            }
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                v.getChildren().add(del);
            }
            Separator sep = new Separator(Orientation.VERTICAL);
            sep.setOpacity(0.4);
            Separator sep1 = new Separator(Orientation.VERTICAL);
            sep1.setOpacity(0.4);
            Separator sep2 = new Separator(Orientation.VERTICAL);
            sep2.setOpacity(0.4);

            user.getChildren().addAll(v1, sep,
                    v2, sep1,
                    v3, sep2,
                    v4);
            user.setMinWidth(600);

            HBox div = new HBox(user, v);
            div.setFillHeight(true);
            div.setSpacing(10);
            div.setAlignment(Pos.CENTER);

            listeU.getChildren().addAll(div);
        }
    }

    /**
     * M�thode back
     * Retour vers l'interface FilmsM.
     * @param e
     */
    public void back (ActionEvent e){
            Main.changeWindow(e, "../xml/FilmsM.fxml");
    }
}
