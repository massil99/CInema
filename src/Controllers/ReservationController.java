package Controllers;


import Seances.Seance;
import Seances.Tarif;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

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

    /**
     * Chargement des information de la seance dans laquelle se fera la reservation
     * @param s La seance a reserver
     */
    public static void loadInfo(Seance s){
        ReservationController.s = s;
    }

    /**
     * Chargemnt de la liste de tarif et la liste de nombre de personne qui reserve
     * @param url
     * @param rb
     */
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

    /**
     * Annulation de la reservation
     * @param e
     */
    public void notReserving(ActionEvent e){
        Main.changeWindow(e, "../xml/FilmsM.fxml");
    }

    /**
     * Validation de la reservation
     * @param e
     */
    public void reserve(ActionEvent e){
        Tarif t = listeTarif.getSelectionModel().getSelectedItem();
        int nb = nbPersonne.getSelectionModel().getSelectedItem();

        if(t != null && nb != 0){
            for(int i = 0; i < nb; i++){
                s.reserver(t);
            }

            Main.changeWindow(e, "../xml/FilmsM.fxml");
        }
    }
}
