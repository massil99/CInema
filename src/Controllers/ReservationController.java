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

public class ReservationController implements Initializable {

    public Label titre;
    public Label start;
    public Label end;
    public ImageView poster;
    public ComboBox<Tarif> listeTarif;
    public ComboBox<Integer> nbPersonne;

    private static  Seance s;

    /**
     * Méthode loadInfo
     * Chargement des informations de la séance dans laquelle se fera la réservation.
     * @param s La séance à réserver.
     */
    public static void loadInfo(Seance s){
        ReservationController.s = s;
    }

    /**
     * Méthode initialize
     * Chargement de la liste des tarifs et la liste du nombre de personnes qui réservent.
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
     * Méthode notReserving
     * Annulation de la réservation.
     * @param e
     */
    public void notReserving(ActionEvent e){
        Main.changeWindow(e, "../xml/FilmsM.fxml");
    }

    /**
     * Méthode reserve
     * Validation de la réservation.
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
