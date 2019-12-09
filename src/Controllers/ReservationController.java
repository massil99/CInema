package Controllers;


import Seances.Seance;
import Seances.Tarif;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationController implements Initializable{

    public Label titre;
    public Label start;
    public Label end;
    public ImageView poster;
    public ComboBox<Tarif> listeTarif;
    public ComboBox<Integer> nbPersonne;

    private static  Seance s;

    public static void loadInfo(Seance s){
        ReservationController.s = s;
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        poster.setImage(new Image("res/"+s.getF().getTitre()));
        titre.setText(s.getF().getTitre());
        start.setText(s.getHeureDebut());
        end.setText(s.getHeureFin());

        listeTarif.getItems().add(Tarif.MoinsDe14);
        listeTarif.getItems().add(Tarif.Matin);
        listeTarif.getItems().add(Tarif.Plein);
        listeTarif.getItems().add(Tarif.Reduit);

        for(int i = 1; i <= 10; i++)
            nbPersonne.getItems().add(i);

    }

    public void notReserving(ActionEvent e){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../xml/FilmsM.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Scene signInScene = new Scene(root,  1300, 700);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

    public void reserve(ActionEvent e){
        Tarif t = listeTarif.getSelectionModel().getSelectedItem();
        int nb = nbPersonne.getSelectionModel().getSelectedItem();

        for(int i = 0; i < nb; i++){
            s.reserver(t);
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../xml/FilmsM.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Scene signInScene = new Scene(root,  1300, 700);
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }
}
